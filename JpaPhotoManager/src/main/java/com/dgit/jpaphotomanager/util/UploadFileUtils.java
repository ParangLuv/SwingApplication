package com.dgit.jpaphotomanager.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	public static String makeThumbnail(String uploadPath, String filename) throws IOException {

		String thumbnailName = "";

		BufferedImage srcImg = ImageIO.read(new File(uploadPath, filename));

		BufferedImage destImg = Scalr.resize(srcImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		thumbnailName = uploadPath + "/" + "s_" + filename;

		File newFile = new File(thumbnailName);
		String format = filename.substring(filename.lastIndexOf(".") + 1);

		ImageIO.write(destImg, format.toUpperCase(), newFile);

		return "s_" + filename;
	}

	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath + calPath(uploadPath) + "/" + savedName);
		FileCopyUtils.copy(fileData, target);

		return calPath(uploadPath) + "/" + makeThumbnail(uploadPath + calPath(uploadPath), savedName);
	}

	private static String calPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "/" + cal.get(Calendar.YEAR);
		String monthPath = String.format("%s/%02d", yearPath, cal.get(Calendar.MONTH) + 1);
		String datePath = String.format("%s/%02d", monthPath, cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		return datePath;
	}

	public static void makeDir(String uploadPath, String... paths) {
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}

}
