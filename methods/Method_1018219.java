/** 
 * initialize the value of its options from a speicfied  jcseg.properties propertie file
 * @param proFile 
 * @throws IOException
 */
public void load(String proFile) throws IOException {
  this.load(new FileInputStream(proFile));
}
