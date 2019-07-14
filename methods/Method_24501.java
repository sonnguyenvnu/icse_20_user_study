/** 
 * Lists all available LED devices
 * @return String array
 * @webref
 */
public static String[] list(){
  if (NativeInterface.isSimulated()) {
    return new String[]{"led0","led1"};
  }
  ArrayList<String> devs=new ArrayList<>();
  File dir=new File("/sys/class/leds");
  File[] files=dir.listFiles();
  if (files != null) {
    for (    File file : files) {
      devs.add(file.getName());
    }
  }
  String[] tmp=devs.toArray(new String[devs.size()]);
  Arrays.sort(tmp);
  return tmp;
}
