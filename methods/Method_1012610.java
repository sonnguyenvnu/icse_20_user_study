/** 
 * string to int
 * @param input
 * @return
 */
public static int stringtoint(String input){
  try {
    return Integer.parseInt(input);
  }
 catch (  Exception e) {
    return -1;
  }
}
