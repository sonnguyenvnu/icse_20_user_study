package com.roncoo.pay.reconciliation.utils;

import com.roncoo.pay.reconciliation.utils.https.HttpResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 
 * @类功能说明： 文件工具类.
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公�?��??称：广州领课网络科技有�?公�?�（龙果学院:www.roncoo.com）
 * @作者：Along.shen
 * @创建时间：2016年5月23日,上�?�11:33:56.
 * @版本：V1.0
 *
 */

public class FileUtils {
	private static final Log LOG = LogFactory.getLog(FileUtils.class);

	/**
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static File saveFile(HttpResponse response, File file) throws IOException {

		// 判断父文件是�?�存在,�?存在就创建
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				// 新建文件目录失败，抛异常
				throw new IOException("创建文件(父层文件夹)失败, filepath: " + file.getAbsolutePath());
			}
		}
		// 判断文件是�?�存在，�?存在则创建
		if (!file.exists()) {
			if (!file.createNewFile()) {
				// 新建文件失败，抛异常
				throw new IOException("创建文件失败, filepath: " + file.getAbsolutePath());
			}
		}

		InputStream is = response.getInputStream();
		FileOutputStream fileOut = null;
		FileChannel fileChannel = null;
		try {
			fileOut = new FileOutputStream(file);
			fileChannel = fileOut.getChannel();

			ReadableByteChannel readableChannel = Channels.newChannel(is);
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 32);
			while (true) {
				buffer.clear();
				if (readableChannel.read(buffer) == -1) {
					readableChannel.close();
					break;
				}
				buffer.flip();
				fileChannel.write(buffer);
			}
			return file;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("�?存账�?�文件失败, filepath: " + file.getAbsolutePath());
		} catch (IOException e) {
			throw new IOException("�?存账�?�文件失败, filepath: " + file.getAbsolutePath(), e);
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					LOG.error("�?存账�?�时关闭�?失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					LOG.error("�?存账�?�时关闭�?失败", e);
				}
			}
			if (fileChannel != null) {
				try {
					fileChannel.close();
				} catch (IOException e) {
					LOG.error("�?存账�?�时关闭�?失败", e);
				}
			}
		}
	}

	/**
	 * 解压到指定目录
	 *
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static List<String> unZipFiles(String zipPath, String descDir) throws IOException {
		return unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录
	 *
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> unZipFiles(File zipFile, String descDir) throws IOException {
		List<String> result = new ArrayList<String>();
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		Charset charset = Charset.forName("GBK");
		ZipFile zip = new ZipFile(zipFile, charset);
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
			;
			// 判断路径是�?�存在,�?存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是�?�为文件夹,如果是上�?�已�?上传,�?需�?解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信�?�
			result.add(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		return result;
	}

	/**
	 * 解�?csv文件 到一个list中 �?个�?�元个为一个String类型记录，�?一行为一个list。 �?将所有的行放到一个总list中
	 *
	 * @param file
	 *            �?解�?的cvs文件
	 * @param charsetName
	 *            指定的字符编�?�
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> readCSVFile(String file, String charsetName) throws IOException {
		if (file == null || !file.contains(".csv")) {
			return null;
		}
		InputStreamReader fr = new InputStreamReader(new FileInputStream(file), charsetName);

		BufferedReader br = new BufferedReader(fr);
		String rec = null;// 一行
		String str;// 一个�?�元格
		List<List<String>> listFile = new ArrayList<List<String>>();
		try {
			// 读�?�一行
			while ((rec = br.readLine()) != null) {
				Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
				Matcher mCells = pCells.matcher(rec);
				List<String> cells = new ArrayList<String>();// �?行记录一个list
				// 读�?��?个�?�元格
				while (mCells.find()) {
					str = mCells.group();
					str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
					str = str.replaceAll("(?sm)(\"(\"))", "$2");
					cells.add(str);
				}
				listFile.add(cells);
			}
		} catch (Exception e) {
			LOG.error("异常", e);
		} finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return listFile;
	}
}
