package com.lingjoin.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileOperateUtils {
	
	/**
	 * @description：得到目录下的所有文件的路径
	 * @param dir
	 *            :�?分�?的文件夹的路径
	 * @return：文件夹中所有文件的�?对路径集�?�
	 * @throws Exception
	 * @author tanshuguo
	 */
	// 
	static ArrayList allFilesPath = new ArrayList();

	public static ArrayList getAllFilesPath(File dir) {

		if (!dir.isDirectory()) {
			String filePath = dir.getAbsolutePath();
//			System.out.println(filePath);
			allFilesPath.add(filePath);
		} else {
			File[] fs = dir.listFiles();
			for (int i = 0; i < fs.length; i++) {

				if (fs[i].isDirectory()) {
					try {
						getAllFilesPath(fs[i]);
					} catch (Exception e) {
					}
				} else {
					String filePath = fs[i].getAbsolutePath();
//					System.out.println(filePath);
					allFilesPath.add(filePath);
				}
			}
		}
		System.out.println("Utils.getAllFilesPath-文件个数---->" + allFilesPath.size());
		return allFilesPath;
	}

	/**
	 * @description：得到文件内容
	 * @param filePath
	 *            :�?读�?�的文件路径
	 * @return 返回文件内容
	 * @author tanshuguo
	 */
	public static String getFileContent(String filePath) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		// String fileContent="";
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是�?�存在
				isr = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编�?格�?
				bufferedReader = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					//System.out.println(lineTxt);
					sb.append(lineTxt);
				}

				isr.close();
			} else {
				System.out.println("找�?到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读�?�文件内容出错");
			e.printStackTrace();
		} finally {
			try {
				if (isr != null) {
					isr.close();
					isr = null;
				}
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//System.out.println("--->" + sb.toString());
		// System.out.println("---->"+);
		return sb.toString();
	}
	/**
	 * @description：得到文件内容
	 * @param filePath
	 *            :�?读�?�的文件路径
	 * @return 返回文件内容
	 * @author tanshuguo
	 */
	public static String getFileContent(String filePath,String encode) {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		// String fileContent="";
		try {
			String encoding = encode;
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是�?�存在
				isr = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编�?格�?
				bufferedReader = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					//System.out.println(lineTxt);
					sb.append(lineTxt);
				}

				isr.close();
			} else {
				System.out.println("找�?到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读�?�文件内容出错");
			e.printStackTrace();
		} finally {
			try {
				if (isr != null) {
					isr.close();
					isr = null;
				}
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//System.out.println("--->" + sb.toString());
		// System.out.println("---->"+);
		return sb.toString();
	}
	/**
	 * 获得文件的字节数组
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByteFromFile(String filename) throws IOException{  
        
        File f = new File(filename);  
        if(!f.exists()){  
            throw new FileNotFoundException(filename);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
        BufferedInputStream in = null;  
        try{  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while(-1 != (len = in.read(buffer,0,buf_size))){  
                bos.write(buffer,0,len);  
            }  
            return bos.toByteArray();  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                in.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  
	/**
	 * @decription:把data写入targetFilePath中
	 * @param data
	 *            ：�?写入的内容，采用编�?为：utf-8
	 * @param targetFilePath
	 *            ：�?写入到的文件路径
	 * @author tanshuguo
	 */
	public static void writeFile(String data, String targetFilePath) {
		OutputStreamWriter osw = null;
		BufferedWriter output = null;
		FileOutputStream fos=null;
		String encoding = "utf-8";
//		String encoding = "gbk";
//		String encoding = "gb2312";

		try {
			File file = new File(targetFilePath);
			if (file.exists()) {
				System.out.println("Utils.writeFile--文件存在，追加内容");
                fos=new FileOutputStream(file, true);
				osw = new OutputStreamWriter(fos,
						encoding);// 考虑到编�?格�?
				output = new BufferedWriter(osw);
				output.write(data + "\r\n");
			} else {
				System.out.println("Utils.writeFile--文件�?存在--已创建");
				File parentOfFile = file.getParentFile();
				if (!parentOfFile.exists()) {
					parentOfFile.mkdirs();
					System.out.println("Utils--writeFile--存储文件父路径-->" + parentOfFile.getPath());

				}
				// file.mkdirs();
				file.createNewFile();// �?存在则创建
				fos=new FileOutputStream(file, true);
				osw = new OutputStreamWriter(fos,
						encoding);// 考虑到编�?格�?
				output = new BufferedWriter(osw);
				output.write(data + "\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
		
				if (output != null) {
					output.close();
					output = null;
				}
				if (osw != null) {
					osw.close();
					osw = null;
				}
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @decription:把data写入targetFilePath中
	 * @param data
	 *            ：�?写入的内容，采用编�?为：utf-8
	 * @param targetFilePath
	 *            ：�?写入到的文件路径
	 * @param encoding:写文件时�?采用的编�?格�?
	 */
	public static void writeFile(String data, String targetFilePath,String encoding) {
		OutputStreamWriter osw = null;
		BufferedWriter output = null;
		FileOutputStream fos=null;
		//String encoding = "utf-8";
//		String encoding = "gbk";
//		String encoding = "gb2312";

		try {
			File file = new File(targetFilePath);
			if (file.exists()) {
				System.out.println("Utils.writeFile--文件存在，追加内容");
                fos=new FileOutputStream(file, true);
				osw = new OutputStreamWriter(fos,
						encoding);// 考虑到编�?格�?
				output = new BufferedWriter(osw);
				output.write(data + "\r\n");
			} else {
				System.out.println("Utils.writeFile--文件�?存在--已创建");
				File parentOfFile = file.getParentFile();
				if (!parentOfFile.exists()) {
					parentOfFile.mkdirs();
					System.out.println("Utils--writeFile--存储文件父路径-->" + parentOfFile.getPath());

				}
				// file.mkdirs();
				file.createNewFile();// �?存在则创建
				fos=new FileOutputStream(file, true);
				osw = new OutputStreamWriter(fos,
						encoding);// 考虑到编�?格�?
				output = new BufferedWriter(osw);
				output.write(data + "\r\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
		
				if (output != null) {
					output.close();
					output = null;
				}
				if (osw != null) {
					osw.close();
					osw = null;
				}
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @decription:把data写入targetFilePath中
	 * @param data
	 *            ：�?写入的内容
	 * @param targetFilePath
	 *            ：�?写入到的文件路径
	 * @param encoding:写文件时�?采用的编�?格�?
	 */
	public static void writeFileNotAppend(String data, String targetFilePath,String encoding) {
		OutputStreamWriter osw = null;
		BufferedWriter output = null;
		FileOutputStream fos=null;

		try {
			File file = new File(targetFilePath);
			if (file.exists()) {
				System.out.println("--writeFileNotAppend--->文件已存在");
				file.delete();
				System.out.println("--writeFileNotAppend--->文件已删除");
			}
			
			System.out.println("--writeFileNotAppend--->文件已创建");
				File parentOfFile = file.getParentFile();
				if (!parentOfFile.exists()) {
					parentOfFile.mkdirs();
					System.out.println("--writeFileNotAppend--->存储文件父路径-->" + parentOfFile.getPath());

				}
				// file.mkdirs();
				file.createNewFile();// �?存在则创建
				fos=new FileOutputStream(file, true);
				osw = new OutputStreamWriter(fos,
						encoding);// 考虑到编�?格�?
				output = new BufferedWriter(osw);
				output.write(data + "\r\n");
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
		
				if (output != null) {
					output.close();
					output = null;
				}
				if (osw != null) {
					osw.close();
					osw = null;
				}
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param zhStr 需�?得到unicode编�?的字符串
	 * @return  unicode编�?的字符串
	 */
	public static String getUnicodeFromStr(String zhStr){ 
		StringBuffer unicode = new StringBuffer(); 
		for(int i=0; i<zhStr.length();i++){ 
		char c = zhStr.charAt(i); 
		unicode.append("\\u" + Integer.toHexString(c)); 
		} 
		return unicode.toString(); 
		} 
	/**
	 * 
	 * @param unicode  字符的unicode编�?：例如北京欢迎你的unicode编�?为：\u5317\u4eac\u6b22\u8fce\u4f60
	 * @return 返回字符串：如北京欢迎你
	 */
	public static String getStrFromUnicode(String unicode){ 
		StringBuffer sb = new StringBuffer(); 
		String[] hex = unicode.split("\\\\u");  // \�?是"\\u"，而是 "\\\\u" 
		for(int i=1;i<hex.length;i++){          // 注�?�?从 1 开始，而�?是从0开始。第一个是空。 
		int data = Integer.parseInt(hex[i],16);  //  将16进制数转�?�为 10进制的数�?�。 
		sb.append((char)data);  //  强制转�?�为char类型就是我们的中文字符了。 
		} 
		//System.out.println("这是从 Unicode编�? 转�?�为 中文字符了: "  +sb.toString()); 
		return sb.toString(); 
		} 
	
	public static void main(String[] args) throws Exception {
         getUnicodeFromStr("中国");
	}

}
