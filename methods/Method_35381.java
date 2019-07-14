private String getToolbarTitle(File parent){
  return parent.getAbsolutePath().equals(SDCARD.getAbsolutePath()) ? DEFAULT_SDCARD_NAME : parent.getName();
}
