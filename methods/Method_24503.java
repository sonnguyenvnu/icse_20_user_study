/** 
 * Disables the PWM output
 * @webref
 */
public void clear(){
  if (NativeInterface.isSimulated()) {
    return;
  }
  String fn=String.format("/sys/class/pwm/%s/pwm%d/enable",chip,channel);
  int ret=NativeInterface.writeFile(fn,"0");
  if (ret < 0) {
    throw new RuntimeException(NativeInterface.getError(ret));
  }
}
