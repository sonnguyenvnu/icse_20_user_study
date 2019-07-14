@Override public String getExportedURLs(String serviceInterface,String group,String version){
  List<URL> urls=getRepository().getExportedURLs(serviceInterface,group,version);
  return jsonUtils.toJSON(urls);
}
