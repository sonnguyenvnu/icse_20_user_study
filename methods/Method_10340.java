private String normalizeContentType(String type){
  return type == null ? RequestParams.APPLICATION_OCTET_STREAM : type;
}
