public void loadHttpFile(String url,String defaultExt,int currentAccount){
  if (url == null || url.length() == 0 || httpFileLoadTasksByKeys.containsKey(url)) {
    return;
  }
  String ext=getHttpUrlExtension(url,defaultExt);
  File file=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(url) + "_temp." + ext);
  file.delete();
  HttpFileTask task=new HttpFileTask(url,file,ext,currentAccount);
  httpFileLoadTasks.add(task);
  httpFileLoadTasksByKeys.put(url,task);
  runHttpFileLoadTasks(null,0);
}
