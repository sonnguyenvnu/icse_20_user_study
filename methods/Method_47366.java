public int checkFolder(final File folder,Context context){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    if (FileUtil.isOnExtSdCard(folder,context)) {
      if (!folder.exists() || !folder.isDirectory()) {
        return DOESNT_EXIST;
      }
      if (!FileUtil.isWritableNormalOrSaf(folder,context)) {
        guideDialogForLEXA(folder.getPath());
        return CAN_CREATE_FILES;
      }
      return WRITABLE_OR_ON_SDCARD;
    }
 else     if (FileUtil.isWritable(new File(folder,"DummyFile"))) {
      return WRITABLE_OR_ON_SDCARD;
    }
 else     return DOESNT_EXIST;
  }
 else   if (Build.VERSION.SDK_INT == 19) {
    if (FileUtil.isOnExtSdCard(folder,context)) {
      return WRITABLE_OR_ON_SDCARD;
    }
 else     if (FileUtil.isWritable(new File(folder,"DummyFile"))) {
      return WRITABLE_OR_ON_SDCARD;
    }
 else     return DOESNT_EXIST;
  }
 else   if (FileUtil.isWritable(new File(folder,"DummyFile"))) {
    return WRITABLE_OR_ON_SDCARD;
  }
 else {
    return DOESNT_EXIST;
  }
}
