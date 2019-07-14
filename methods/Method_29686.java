/** 
 */
public void toggleFacing(){
  if (getFacing() == CameraKit.FACING_BACK) {
    setFacing(CameraKit.FACING_FRONT);
  }
 else {
    setFacing(CameraKit.FACING_BACK);
  }
}
