public boolean isLoadingHttpFile(String url){
  return httpFileLoadTasksByKeys.containsKey(url);
}
