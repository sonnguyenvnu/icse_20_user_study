private void mergeHeadersWithPrefix(List<String> header,String prefix,String[] newHeaders){
  for (int i=0; i < newHeaders.length; i++) {
    String newHeader=newHeaders[i];
    if (prefix != null && !prefix.equals("")) {
      newHeader=prefix + "." + newHeader;
    }
    if (!header.contains(newHeader)) {
      header.add(newHeader);
    }
  }
}
