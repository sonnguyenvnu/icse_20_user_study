public FileMetaData input(int which,int i){
  checkArgument(which == 0 || which == 1,"which must be either 0 or 1");
  if (which == 0) {
    return levelInputs.get(i);
  }
 else {
    return levelUpInputs.get(i);
  }
}
