@Override public void addDateHeader(final String name,final long value){
  if (name.equalsIgnoreCase(LAST_MODIFIED)) {
    lastModifiedData.updateLastModified(value);
  }
 else {
    super.addDateHeader(name,value);
  }
}
