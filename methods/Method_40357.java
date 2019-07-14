Module getSerializedModule(String sourcePath){
  try {
    File sourceFile=new File(sourcePath);
    if (sourceFile == null || !sourceFile.canRead()) {
      return null;
    }
    File cached=new File(getCachePath(sourceFile));
    if (!cached.canRead()) {
      return null;
    }
    return deserialize(sourceFile);
  }
 catch (  Exception x) {
    return null;
  }
}
