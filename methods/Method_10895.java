/** 
 * ??????????
 * @param mobiles
 * @return
 */
public static boolean isMobile(String mobiles){
  Pattern p=Pattern.compile("^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$");
  Matcher m=p.matcher(mobiles);
  return m.matches();
}
