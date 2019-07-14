/** 
 * This function should throw an exception or return a value. Do not return null.
 */
public File getSettingsFolder() throws Exception {
  File home=new File(System.getProperty("user.home"));
  return new File(home,".processing");
}
