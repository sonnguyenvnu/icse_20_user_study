@Override public void setHeader(final String name,final String value){
  if (name.equalsIgnoreCase(CONTENT_TYPE)) {
    setContentType(value);
  }
 else {
    super.setHeader(name,value);
  }
}
