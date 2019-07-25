
package com.jpay.ext.kit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jpay.util.IOUtils;

/**
 * google 开�?图形�?工具Zxing使用
 */
public class ZxingKit {
	/**
	 * Zxing图形�?生�?工具
	 *
	 * @param contents
	 *            内容
	 * @param barcodeFormat
	 *            BarcodeFormat对象
	 * @param format
	 *            图片格�?，�?�选[png,jpg,bmp]
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param margin
	 *            边框间�?px
	 * @param saveImgFilePath
	 *            存储图片的完整�?置，包�?�文件�??
	 * @return {boolean}
	 */
	public static boolean encode(String contents, BarcodeFormat barcodeFormat, Integer margin,
			ErrorCorrectionLevel errorLevel, String format, int width, int height, String saveImgFilePath) {
		Boolean bool = false;
		BufferedImage bufImg;
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, errorLevel);
		hints.put(EncodeHintType.MARGIN, margin);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			// contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, barcodeFormat, width, height, hints);
			MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
			bufImg = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
			bool = writeToFile(bufImg, format, saveImgFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 *
	 * @param outputStream
	 * 			�?�以�?�自response，也�?�以�?�自文件
	 * @param contents
	 *			内容
	 * @param barcodeFormat
	 * 			BarcodeFormat对象
	 * @param margin
	 * 			图片格�?，�?�选[png,jpg,bmp]
	 * @param errorLevel
	 *			纠错级别 一般为：ErrorCorrectionLevel.H
	 * @param format
	 * 			图片格�?，�?�选[png,jpg,bmp]
	 * @param width
	 * 			宽
	 * @param height
	 * 			高
	 * 	eg:
	 * 		ZxingKit.encodeOutPutSteam(response.getOutputStream(), qrCodeUrl, BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H, "png", 200, 200);
	 */
	public static void encodeOutPutSteam(OutputStream outputStream, String contents, BarcodeFormat barcodeFormat, Integer margin, ErrorCorrectionLevel errorLevel, String format, int width, int height) {
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, errorLevel);
		hints.put(EncodeHintType.MARGIN, margin);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		try {
			BitMatrix bitMatrix = (new MultiFormatWriter()).encode(contents, barcodeFormat, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}

	}

	/**
	 * @param srcImgFilePath
	 *            �?解�?的图片地�?�
	 * @return {Result}
	 */
	@SuppressWarnings("finally")
	public static Result decode(String srcImgFilePath) {
		Result result = null;
		BufferedImage image;
		try {
			File srcFile = new File(srcImgFilePath);
			image = ImageIO.read(srcFile);
			if (null != image) {
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
				hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
				result = new MultiFormatReader().decode(bitmap, hints);
			} else {
				throw new IllegalArgumentException ("Could not decode image.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	/**
	 * 将BufferedImage对象写入文件
	 *
	 * @param bufImg
	 *            BufferedImage对象
	 * @param format
	 *            图片格�?，�?�选[png,jpg,bmp]
	 * @param saveImgFilePath
	 *            存储图片的完整�?置，包�?�文件�??
	 * @return {boolean}
	 */
	@SuppressWarnings("finally")
	public static boolean writeToFile(BufferedImage bufImg, String format, String saveImgFilePath) {
		Boolean bool = false;
		try {
			bool = ImageIO.write(bufImg, format, new File(saveImgFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return bool;
		}
	}

	public static void main(String[] args) {
		String saveImgFilePath = "D://zxing.png";
		Boolean encode = encode("我是Javen205", BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H, "png", 200, 200,
				saveImgFilePath);
		if (encode) {
			Result result = decode(saveImgFilePath);
			String text = result.getText();
			System.out.println(text);
		}
	}
}
