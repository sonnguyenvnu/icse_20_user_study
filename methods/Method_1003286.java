/** 
 * Get the unwrapped file name (without wrapper prefixes if wrapping / delegating file systems are used).
 * @param fileName the file name
 * @return the unwrapped
 */
public static String unwrap(String fileName){
  return FilePath.get(fileName).unwrap().toString();
}
