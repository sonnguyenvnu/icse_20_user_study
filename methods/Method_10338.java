public void addPart(String key,File file,String type,String customFileName){
  fileParts.add(new FilePart(key,file,normalizeContentType(type),customFileName));
}
