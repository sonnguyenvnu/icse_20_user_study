public File[] getApplicationExports(int platform,String variant){
  String[] list=getApplicationExportList(platform,variant);
  return wrapFiles(list);
}
