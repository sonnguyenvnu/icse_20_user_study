protected boolean fileReadOnly(){
  return !file.canWrite();
}
