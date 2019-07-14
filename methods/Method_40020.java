public void analyze(String path){
  String upath=_.unifyPath(path);
  File f=new File(upath);
  projectDir=f.isDirectory() ? f.getPath() : f.getParent();
  loadFileRecursive(upath);
}
