private String chooseEncoding(Page page,Metadata metadata){
  String pageCharset=page.getContentCharset();
  if (pageCharset == null || pageCharset.isEmpty()) {
    return metadata.get("Content-Encoding");
  }
  return pageCharset;
}
