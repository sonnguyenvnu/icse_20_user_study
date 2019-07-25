@Override public FileChannel open(String mode){
  FileMemData obj=getMemoryFile();
  return new FileMem(obj,"r".equals(mode));
}
