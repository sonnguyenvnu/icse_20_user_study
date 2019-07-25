@Override public void run(){
  long modifiedLocal=file.lastModified();
  if (this.modified != modifiedLocal) {
    this.modified=modifiedLocal;
    observer.fileChanged(file);
  }
}
