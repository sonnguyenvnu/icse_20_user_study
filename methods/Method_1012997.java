public void fill(){
  CommonFields.mimeTypes().forEach(this::addUnique);
}
