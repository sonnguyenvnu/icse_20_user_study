@Override public void setDateHeader(final String name,final long value){
  if (name.equalsIgnoreCase(LAST_MODIFIED)) {
    lastModifiedData.updateLastModified(value);
  }
 else {
    super.setDateHeader(name,value);
  }
}
