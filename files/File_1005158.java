package org.jeecgframework.core.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * 
 * @author  张代浩
 *
 */
public class LogUtil {

  //private static BaseLogUtil objLog = new BaseLogUtil();
  private static final String LOGCONFIG = "log4j.properties";
  private static Logger objLog;
  
  private static Logger getLogger() {
    if(objLog==null){
//      DOMConfigurator.configure(getConfigFile());
      objLog = LogManager.getLogger(LogUtil.class);
    }
    return objLog;
  }
  
  private static String getConfigFile(){
    String s = LogUtil.class.getClassLoader().getResource("").toString();
    String filePath = s + LOGCONFIG;
    return filePath;
  }
  
  // Start Info
  // /
  // / 记录信�?�
  // /
  // / 信�?�内容
  public static void info(String message, Exception exception) {
    try {
      log("INFO", message, exception);
    } catch (Exception ex) {

    }
  }

  // /
  // / 记录信�?�
  // /
  // / 信�?�内容
  public static void info(Object message) {
    log("INFO", message);
  }
  // end Info

  // Start Error 
  public static void trace(String message) {
    try {
      log("TRACE", message);
    } catch (Exception ex) {
    }
  }

  public static void trace(String message, Exception exception) {
    try {
      log("TRACE", message, exception);
    } catch (Exception ex) {
    }
  }

  // /
  // / 记录一个错误信�?�
  // /
  // / 信�?�内容
  // / 异常对象
  public static void error(String message, Exception exception) {
    try {
      log("ERROR", message, exception);
    } catch (Exception ex) {

    }
  }

  // /
  // / 记录一个错误信�?�
  // /
  // / 信�?�内容
  public static void error(String message) {
    try {
      log("ERROR", message);
    } catch (Exception ex) {

    }
  }

  // end Error

  // Start Warning

  // /
  // / 记录一个警告信�?�
  // /
  // / 信�?�内容
  // / 异常对象
  public static void warning(String message, Exception exception) {
    try {
      log("WARN", message, exception);
    } catch (Exception ex) {

    }
  }

  // /
  // / 记录一个警告信�?�
  // /
  // / 信�?�内容
  public static void warning(String message) {
    try {
      log("WARN", message);
    } catch (Exception ex) {

    }
  }

  // end Warning

  // Start Fatal

  // /
  // / 记录一个程�?致命性错误
  // /
  // / 信�?�内容
  // / 异常对象
  public static void fatal(String message, Exception exception) {
    try {
      log("FATAL", message, exception);
    } catch (Exception ex) {

    }
  }

  // /
  // / 记录一个程�?致命性错误
  // /
  // / 信�?�内容
  public static void fatal(String message) {
    try {
      log("FATAL", message);
    } catch (Exception ex) {

    }
  }

  // end Fatal

  // Start Debug

  // /
  // / 记录调试信�?�
  // /
  // / 信�?�内容
  // / 异常对象
  public static void debug(String message, Exception exception) {
    try {
      log("DEBUG", message, exception);
    } catch (Exception ex) {

    }
  }

  // /
  // / 记录调试信�?�
  // /
  // / 信�?�内容
  public static void debug(String message) {
    try {
      log("DEBUG", message);
    } catch (Exception ex) {

    }
  }
  // end Debug

  public static void log(String level, Object msg)
  {
    log(level, msg, null);
  }

  public static void log(String level, Throwable e)
  {
    log(level, null, e);
  }

  public static void log(String level, Object msg, Throwable e)
  {
	 StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);
    try{
      StringBuilder sb = new StringBuilder();
      Throwable t = new Throwable();
      t.printStackTrace(pw);
      String input = sw.getBuffer().toString();
      StringReader sr = new StringReader(input);
      BufferedReader br = new BufferedReader(sr);
      for(int i=0;i<4;i++)
        br.readLine(); 
      String line = br.readLine();
      //at com.media.web.action.DicManageAction.list(DicManageAction.java:89)
      int paren = line.indexOf("at ");
      line = line.substring(paren+3);
      paren = line.indexOf('(');
      String invokeInfo = line.substring(0, paren);
      int period = invokeInfo.lastIndexOf('.');
      sb.append('[');
      sb.append(invokeInfo.substring(0,period));
      sb.append(':');
      sb.append(invokeInfo.substring(period+1));
      sb.append("():");
      paren = line.indexOf(':');
      period = line.lastIndexOf(')');
      sb.append(line.substring(paren+1,period));
      sb.append(']');
      sb.append(" - ");
      sb.append(msg);
      getLogger().log((Priority) Level.toLevel(level), sb.toString(), e);
    }catch(Exception ex){
      ex.printStackTrace();
    }finally{
    	try {
    		sw.close();
        	pw.close();
		} catch (Exception e2) {
		}
    }
  }
}
