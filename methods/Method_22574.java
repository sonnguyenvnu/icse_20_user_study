public boolean supportsArch(int platform,String variant){
  if (multipleArch[platform] == false) {
    return true;
  }
  return getApplicationExportList(platform,variant) != null;
}
