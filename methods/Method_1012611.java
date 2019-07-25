/** 
 * string to long
 * @param input
 * @return
 */
public static long stringtolong(String input){
  try {
    return Long.parseLong(input);
  }
 catch (  Exception e) {
    return 0l;
  }
}
