public DecoraManager registerDecorator(final String path,final File decorator){
  if (filesMap == null) {
    filesMap=new HashMap<>();
  }
  filesMap.put(path,decorator);
  return this;
}
