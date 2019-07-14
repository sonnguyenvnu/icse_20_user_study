/** 
 * Read the screen rotation so we can correct the device heading.
 */
@SuppressWarnings("SuspiciousNameCombination") private void readDisplayRotation(){
  mAxisX=SensorManager.AXIS_X;
  mAxisY=SensorManager.AXIS_Y;
switch (mDisplay.getRotation()) {
case Surface.ROTATION_0:
    break;
case Surface.ROTATION_90:
  mAxisX=SensorManager.AXIS_Y;
mAxisY=SensorManager.AXIS_MINUS_X;
break;
case Surface.ROTATION_180:
mAxisY=SensorManager.AXIS_MINUS_Y;
break;
case Surface.ROTATION_270:
mAxisX=SensorManager.AXIS_MINUS_Y;
mAxisY=SensorManager.AXIS_X;
break;
default :
break;
}
}
