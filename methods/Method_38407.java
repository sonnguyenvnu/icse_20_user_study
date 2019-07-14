public DecoraManager registerDecorator(final String path,final char[] content){
  if (contentMap == null) {
    contentMap=new HashMap<>();
  }
  contentMap.put(path,content);
  return this;
}
