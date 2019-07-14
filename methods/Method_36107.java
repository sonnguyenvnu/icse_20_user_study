@SuppressWarnings("unchecked") @Override public Set<String> getAllHeaderKeys(){
  LinkedHashSet<String> headerKeys=new LinkedHashSet<>();
  for (Enumeration<String> headerNames=request.getHeaderNames(); headerNames.hasMoreElements(); ) {
    headerKeys.add(headerNames.nextElement());
  }
  return headerKeys;
}
