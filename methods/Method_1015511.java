protected void remove(String clustername,Address addr){
  if (clustername == null || addr == null)   return;
  File dir=new File(root_dir,clustername);
  if (!dir.exists())   return;
  String filename=addressToFilename(addr);
  File file=new File(dir,filename);
  deleteFile(file);
}
