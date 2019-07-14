protected boolean accepted(String filters,String path){
  StringTokenizer tokenizer=new StringTokenizer(filters);
  while (tokenizer.hasMoreTokens()) {
    String filter=tokenizer.nextToken();
    if (filter.length() > 1) {
      String prefix=filter.substring(1).replace('.','/');
      if (prefix.charAt(prefix.length() - 1) != '/') {
        prefix+='/';
      }
      if (path.startsWith(prefix)) {
        return (filter.charAt(0) == '+');
      }
    }
  }
  return false;
}
