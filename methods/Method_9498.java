public static boolean isShowingImage(TLRPC.FileLocation object){
  boolean result=false;
  if (Instance != null) {
    result=Instance.isVisible && !Instance.disableShowCheck && object != null && Instance.currentFileLocation != null && object.local_id == Instance.currentFileLocation.location.local_id && object.volume_id == Instance.currentFileLocation.location.volume_id && object.dc_id == Instance.currentFileLocation.dc_id;
  }
  return result;
}
