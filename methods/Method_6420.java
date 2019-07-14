public static int typeToIndex(int type){
  if (type == AUTODOWNLOAD_TYPE_PHOTO) {
    return PRESET_SIZE_NUM_PHOTO;
  }
 else   if (type == AUTODOWNLOAD_TYPE_AUDIO) {
    return PRESET_SIZE_NUM_AUDIO;
  }
 else   if (type == AUTODOWNLOAD_TYPE_VIDEO) {
    return PRESET_SIZE_NUM_VIDEO;
  }
 else   if (type == AUTODOWNLOAD_TYPE_DOCUMENT) {
    return PRESET_SIZE_NUM_DOCUMENT;
  }
  return PRESET_SIZE_NUM_PHOTO;
}
