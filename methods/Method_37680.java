protected String nosep(final String in){
  if (in.endsWith(File.separator)) {
    return in.substring(0,in.length() - 1);
  }
  return in;
}
