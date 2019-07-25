/** 
 * Static factory method to obtain an instance of Zips.
 * @param src zip file to process
 * @return instance of Zips
 */
public static Zips get(File src){
  return new Zips(src);
}
