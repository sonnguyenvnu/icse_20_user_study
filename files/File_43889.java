package org.knowm.xchange.coinsuper.utils;

/**
 * 常用工具类
 *
 * @author Lynn Li
 */
public class RestCommonUtil {
  /** 工具类，�?闭构造器 */
  private RestCommonUtil() {}

  /**
   * 返回字符串是�?�为空
   *
   * @param str
   * @return
   */
  public static boolean isEmpty(String str) {
    return str == null || "".equals(str) || "".equals(str.trim());
  }

  /**
   * 返回字符串是�?��?�空
   *
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }
}
