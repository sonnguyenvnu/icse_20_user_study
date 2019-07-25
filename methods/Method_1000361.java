/** 
 * ???? locked -- actived-- ignoreNull
 * @param str ?????????
 * @return true,????
 */
public boolean match(String str){
  if (null != locked && locked.matcher(str).find()) {
    return false;
  }
  if (null != actived && !actived.matcher(str).find()) {
    return false;
  }
  return true;
}
