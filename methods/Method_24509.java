/** 
 * Lists all available SPI interfaces
 * @return String array
 * @webref
 */
public static String[] list(){
  if (NativeInterface.isSimulated()) {
    return new String[]{"spidev0.0","spidev0.1"};
  }
  ArrayList<String> devs=new ArrayList<String>();
  File dir=new File("/dev");
  File[] files=dir.listFiles();
  if (files != null) {
    for (    File file : files) {
      if (file.getName().startsWith("spidev")) {
        devs.add(file.getName());
      }
    }
  }
  String[] tmp=devs.toArray(new String[devs.size()]);
  Arrays.sort(tmp);
  return tmp;
}
