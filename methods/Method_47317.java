private static long getBaseFileSize(HybridFileParcelable baseFile,Context context){
  if (baseFile.isDirectory(context)) {
    return baseFile.folderSize(context);
  }
 else {
    return baseFile.length(context);
  }
}
