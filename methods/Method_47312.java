public static long folderSize(HybridFile directory,OnProgressUpdate<Long> updateState){
  if (directory.isSimpleFile())   return folderSize(new File(directory.getPath()),updateState);
 else   return directory.folderSize(AppConfig.getInstance());
}
