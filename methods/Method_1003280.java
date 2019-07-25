private FilePathWrapper create(String path,FilePath base){
  try {
    FilePathWrapper p=getClass().getDeclaredConstructor().newInstance();
    p.name=path;
    p.base=base;
    return p;
  }
 catch (  Exception e) {
    throw new IllegalArgumentException("Path: " + path,e);
  }
}
