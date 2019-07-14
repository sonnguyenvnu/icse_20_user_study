public void onFileDropped(File file){
  if (file != null) {
    this.getModel().loadFile(file);
  }
}
