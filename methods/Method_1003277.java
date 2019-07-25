@Override public long size(){
  long length=0;
  for (int i=0; ; i++) {
    FilePath f=getBase(i);
    if (f.exists()) {
      length+=f.size();
    }
 else {
      break;
    }
  }
  return length;
}
