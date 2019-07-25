/** 
 * Copy files to the specified target directory.
 * @param targetDir the target directory
 * @param files the list of files to copy
 * @param baseDir the base directory
 */
protected void copy(String targetDir,FileList files,String baseDir){
  File target=new File(targetDir);
  File base=new File(baseDir);
  println("Copying " + files.size() + " files to " + target.getPath());
  String basePath=base.getPath();
  for (  File f : files) {
    File t=new File(target,removeBase(basePath,f.getPath()));
    byte[] data=readFile(f);
    mkdirs(t.getParentFile());
    writeFile(t,data);
  }
}
