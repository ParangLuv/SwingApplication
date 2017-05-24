package com.dgit.jpaphotomanager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.jpaphotomanager.entity.Photo;
import com.dgit.jpaphotomanager.service.PhotoService;
import com.dgit.jpaphotomanager.util.MediaUtils;
import com.dgit.jpaphotomanager.util.UploadFileUtils;

@Controller
public class UploadController {

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Autowired
	PhotoService photoService;

	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String filename) throws IOException {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			String format = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(format);

			HttpHeaders header = new HttpHeaders();
			header.setContentType(mType);

			in = new FileInputStream(uploadPath + "/" + filename);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return entity;
	}

	@RequestMapping(value = "/multiUploadForm", method = RequestMethod.POST)
	public String MultiUploadFormResult(Model model, String id, List<MultipartFile> pic, HttpServletRequest request)
			throws Exception {

		System.out.println("Model : " + model);
		System.out.println("id : " + id);
		System.out.println("pic : " + pic);
		
		
		File upload = new File(uploadPath);
		if (!upload.isDirectory()) {
			upload.mkdirs();
		}

		ArrayList<String> filenames = new ArrayList<String>();
		for (MultipartFile file : pic) {
			UUID uid = UUID.randomUUID();
			String savedName = uid.toString() + "_" + file.getOriginalFilename();
			File target = new File(uploadPath + "/" + savedName);
			FileCopyUtils.copy(file.getBytes(), target);
			String thumFile = UploadFileUtils.makeThumbnail(uploadPath, savedName);
			filenames.add(thumFile);
			photoService.insertPhoto(new Photo(id, thumFile));
		}

		return "redirect:pic";
	}

	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public String deleteFile(String pic) throws Exception {

		new File(uploadPath + "/" + pic).delete();
		new File(uploadPath + "/" + pic.replaceFirst("s_", "")).delete();
		Photo photo = photoService.selectPhoto(pic);
		photoService.deletePhoto(photo);
		return "redirect:pic";

	}
}
