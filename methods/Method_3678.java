/** 
 * ??????
 * @param str
 * @return
 */
public static boolean isAllChinese(String str){
  return str.matches("[\\u4E00-\\u9FA5]+");
}
