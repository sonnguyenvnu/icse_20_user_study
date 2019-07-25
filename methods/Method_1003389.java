/** 
 * Create the directory including the parent directories if they don't exist.
 * @param dir the directory to create
 */
protected static void mkdir(String dir){
  File f=new File(dir);
  if (f.exists()) {
    if (f.isFile()) {
      throw new RuntimeException("Can not create directory " + dir + " because a file with this name exists");
    }
  }
 else {
    mkdirs(f);
  }
}
