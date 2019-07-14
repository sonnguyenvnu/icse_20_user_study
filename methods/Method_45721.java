/** 
 * byte?????????????
 * @param bs ????
 * @param head ??????
 * @return ????
 */
public static boolean startsWith(byte[] bs,byte[] head){
  if (bs.length < head.length) {
    return false;
  }
  for (int i=0; i < head.length; i++) {
    if (head[i] != bs[i]) {
      return false;
    }
  }
  return true;
}
