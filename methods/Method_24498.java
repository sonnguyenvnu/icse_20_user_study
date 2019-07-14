/** 
 * Lists all available I2C interfaces
 * @return String array
 * @webref
 */
public static String[] list(){
  if (NativeInterface.isSimulated()) {
    return new String[]{"i2c-1"};
  }
  ArrayList<String> devs=new ArrayList<String>();
  File dir=new File("/dev");
  File[] files=dir.listFiles();
  if (files != null) {
    for (    File file : files) {
      if (file.getName().startsWith("i2c-")) {
        devs.add(file.getName());
      }
    }
  }
  String[] tmp=devs.toArray(new String[devs.size()]);
  Arrays.sort(tmp);
  return tmp;
}
