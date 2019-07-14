/** 
 * @param facing one of {@link CameraKit.Facing}'s constants.
 * @see CameraKit#FACING_BACK
 * @see CameraKit#FACING_FRONT
 */
public void setFacing(@CameraKit.Facing int facing){
  mFacing=facing;
switch (mCameraPreview.getLifecycleState()) {
case PAUSED:
case STARTED:
{
      onStop();
      onStart();
      break;
    }
case RESUMED:
{
    onStop();
    onStart();
    onResume();
    break;
  }
}
}
