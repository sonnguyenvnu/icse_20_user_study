Module deserialize(File sourcePath) throws Exception {
  String cachePath=getCachePath(sourcePath);
  FileInputStream fis=null;
  ObjectInputStream ois=null;
  try {
    fis=new FileInputStream(cachePath);
    ois=new ObjectInputStream(fis);
    Module mod=(Module)ois.readObject();
    mod.setFile(sourcePath);
    return mod;
  }
  finally {
    if (ois != null) {
      ois.close();
    }
 else     if (fis != null) {
      fis.close();
    }
  }
}
