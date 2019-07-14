/** 
 * Lists all available PWM channels
 * @return String array
 * @webref
 */
public static String[] list(){
  if (NativeInterface.isSimulated()) {
    return new String[]{"pwmchip0/pwm0","pwmchip0/pwm1"};
  }
  ArrayList<String> devs=new ArrayList<String>();
  File dir=new File("/sys/class/pwm");
  File[] chips=dir.listFiles();
  if (chips != null) {
    for (    File chip : chips) {
      try {
        Path path=Paths.get("/sys/class/pwm/" + chip.getName() + "/npwm");
        String tmp=new String(Files.readAllBytes(path));
        int npwm=Integer.parseInt(tmp.trim());
        for (int i=0; i < npwm; i++) {
          devs.add(chip.getName() + "/pwm" + i);
        }
      }
 catch (      Exception e) {
      }
    }
  }
  String[] tmp=devs.toArray(new String[devs.size()]);
  Arrays.sort(tmp);
  return tmp;
}
