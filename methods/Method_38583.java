@Override public void setDynamicAttribute(final String uri,final String localName,final Object value){
  params.add(new String[]{localName,StringUtil.toSafeString(value)});
}
