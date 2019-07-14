private String getRootSubtitle(String path){
  try {
    StatFs stat=new StatFs(path);
    long total=(long)stat.getBlockCount() * (long)stat.getBlockSize();
    long free=(long)stat.getAvailableBlocks() * (long)stat.getBlockSize();
    if (total == 0) {
      return "";
    }
    return LocaleController.formatString("FreeOfTotal",R.string.FreeOfTotal,AndroidUtilities.formatFileSize(free),AndroidUtilities.formatFileSize(total));
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return path;
}
