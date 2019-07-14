void serialize(@NotNull Node ast){
  String path=getCachePath(_.getSHA(ast.file),new File(ast.file).getName());
  ObjectOutputStream oos=null;
  FileOutputStream fos=null;
  try {
    fos=new FileOutputStream(path);
    oos=new ObjectOutputStream(fos);
    oos.writeObject(ast);
  }
 catch (  Exception e) {
    _.msg("Failed to serialize: " + path);
  }
 finally {
    try {
      if (oos != null) {
        oos.close();
      }
 else       if (fos != null) {
        fos.close();
      }
    }
 catch (    Exception e) {
    }
  }
}
