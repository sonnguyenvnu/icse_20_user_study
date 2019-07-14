private String getContentDisposition(final String dataHeader){
  int start=dataHeader.indexOf(':') + 1;
  int end=dataHeader.indexOf(';');
  return dataHeader.substring(start,end);
}
