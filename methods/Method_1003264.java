@Override public FileChannel open(String mode){
  FileNioMemData obj=getMemoryFile();
  return new FileNioMem(obj,"r".equals(mode));
}
