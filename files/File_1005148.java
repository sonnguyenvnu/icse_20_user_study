package org.jeecgframework.core.extend.swftools;

import java.io.File;
import java.io.IOException;

import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.PinyinUtil;


public class SWFToolsSWFConverter implements SWFConverter {
	/** SWFTools pdf2swf.exe路径 */
	private static String PDF2SWF_PATH = ConStant.SWFTOOLS_PDF2SWF_PATH;

	/**
     * 判断是�?�是windows�?作系统
      * @author chenj
     * @return
     */
   private  boolean isWindowsSystem() {
       String p = System.getProperty("os.name");
       return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
   }

	public void convert2SWF(String inputFile, String swfFile, String extend) {
		File pdfFile = new File(inputFile);
		File outFile = new File(swfFile);
		
		if (!pdfFile.exists()) {
			 org.jeecgframework.core.util.LogUtil.info("PDF文件�?存在�?");
			return;
		}
	
		if (outFile.exists()) {
			 org.jeecgframework.core.util.LogUtil.info("SWF文件已存在�?");
			return;
		}
		try {
			// 开始转�?�文档

			//Process process = Runtime.getRuntime().exec(command);
			Process process = null;
	        if (isWindowsSystem()) {
	            //如果是windows系统
	              //命令行命令
	        	//String cmd = exePath + " \"" + fileDir + "\" -o \"" + filePath + "/" + fileName + ".swf\" -T 9 -f";
	        	//ConStant.getSWFToolsPath(extend)
	        	String command = ConStant.getSWFToolsPath(extend) + " \"" + inputFile

	        					+ "\" -o " +" \""+ swfFile +" \""+ " -s languagedir=D:\\xpdf-chinese-simplified -T 9 -f";
//	        					+ "\" -o " + swfFile + " -s languagedir=D:\\xpdf-chinese-simplified -T 9 -f";

	            //Runtime执行�?�返回创建的进程对象
	        	process = Runtime.getRuntime().exec(command);
	        } else {
	            //如果是linux系统,路径�?能有空格，而且一定�?能用�?�引�?�，�?�则无法创建进程
				  String[] command = new String[3];
				command[0] = ConStant.getSWFToolsForLinux(extend);
				command[1] = inputFile;
	              command[2] = swfFile;
	            //Runtime执行�?�返回创建的进程对象
	              process = Runtime.getRuntime().exec(command);
	        }

			
			
			StreamGobbler errorGobbler = new StreamGobbler(
					process.getErrorStream(), "Error");
			StreamGobbler outputGobbler = new StreamGobbler(
					process.getInputStream(), "Output");
			errorGobbler.start();
			outputGobbler.start();
			try {
				process.waitFor();
				org.jeecgframework.core.util.LogUtil.info("时间-------"+process.waitFor());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void convert2SWF(String inputFile, String extend) {
		String swfFile = PinyinUtil.getPinYinHeadChar(FileUtils.getFilePrefix2(inputFile)) + ".swf";
		convert2SWF(inputFile, swfFile, extend);
	}

	/**
     * 测试main方法
      * @param args
     */
   public static void main(String[] args) {
       //转�?�器安装路径
        String exePath = "D:/SWFTools/pdf2swf.exe";
        new SWFToolsSWFConverter().convert2SWF("C:/Users/chenj/Desktop/jeecg/陈劲任务.pdf", exePath);
   }

}
