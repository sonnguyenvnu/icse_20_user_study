public static long[] getSpaces(HybridFile hFile,Context context,final OnProgressUpdate<Long[]> updateState){
  long totalSpace=hFile.getTotal(context);
  long freeSpace=hFile.getUsableSpace();
  long fileSize=0l;
  if (hFile.isDirectory(context)) {
    fileSize=hFile.folderSize(context);
  }
 else {
    fileSize=hFile.length(context);
  }
  return new long[]{totalSpace,freeSpace,fileSize};
}
