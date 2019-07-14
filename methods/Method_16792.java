/** 
 * ????????,???????
 * @param filename
 * @return
 */
public static String getSuffixByFilename(String filename){
  return filename.substring(filename.lastIndexOf(".")).toLowerCase();
}
