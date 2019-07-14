/** 
 * Moves a servo motor to a given orientation
 * @param angle angle in degrees (controls speed and direction on continuous-rotation servos)
 * @webref
 */
public void write(float angle){
  if (attached() == false) {
    System.err.println("You need to call attach(pin) before write(angle).");
    throw new RuntimeException("Servo is not attached");
  }
  if (angle < 0 || 180 < angle) {
    System.err.println("Only degree values between 0 and 180 can be used.");
    throw new IllegalArgumentException("Illegal value");
  }
  pulse=(int)(minPulse + (angle / 180.0) * (maxPulse - minPulse));
  if (handle < 0) {
    GPIO.pinMode(pin,GPIO.OUTPUT);
    if (NativeInterface.isSimulated()) {
      return;
    }
    handle=NativeInterface.servoStartThread(pin,pulse,period);
    if (handle < 0) {
      throw new RuntimeException(NativeInterface.getError((int)handle));
    }
  }
 else {
    int ret=NativeInterface.servoUpdateThread(handle,pulse,period);
    if (ret < 0) {
      throw new RuntimeException(NativeInterface.getError(ret));
    }
  }
}
