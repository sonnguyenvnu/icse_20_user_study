/** 
 * <p>Chooses a camera ID by the specified camera facing ( {@link #mFacing}).</p> <p>This rewrites  {@link #mCameraId},  {@link #mCameraCharacteristics}, and optionally {@link #mFacing}.</p>
 */
private boolean chooseCameraIdByFacing(){
  try {
    int internalFacing=INTERNAL_FACINGS.get(mFacing);
    final String[] ids=mCameraManager.getCameraIdList();
    if (ids.length == 0) {
      throw new RuntimeException("No camera available.");
    }
    for (    String id : ids) {
      CameraCharacteristics characteristics=mCameraManager.getCameraCharacteristics(id);
      Integer level=characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
      if (level == null || level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
        continue;
      }
      Integer internal=characteristics.get(CameraCharacteristics.LENS_FACING);
      if (internal == null) {
        throw new NullPointerException("Unexpected state: LENS_FACING null");
      }
      if (internal == internalFacing) {
        mCameraId=id;
        mCameraCharacteristics=characteristics;
        return true;
      }
    }
    mCameraId=ids[0];
    mCameraCharacteristics=mCameraManager.getCameraCharacteristics(mCameraId);
    Integer level=mCameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
    if (level == null || level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
      return false;
    }
    Integer internal=mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
    if (internal == null) {
      throw new NullPointerException("Unexpected state: LENS_FACING null");
    }
    for (int i=0, count=INTERNAL_FACINGS.size(); i < count; i++) {
      if (INTERNAL_FACINGS.valueAt(i) == internal) {
        mFacing=INTERNAL_FACINGS.keyAt(i);
        return true;
      }
    }
    mFacing=Constants.FACING_BACK;
    return true;
  }
 catch (  CameraAccessException e) {
    throw new RuntimeException("Failed to get a list of camera devices",e);
  }
}
