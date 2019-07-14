/** 
 * ??????
 * @param limit
 * @return
 */
public static String getLimitString(int page,int count){
  return count <= 0 ? "" : " LIMIT " + count + " OFFSET " + getOffset(page,count);
}
