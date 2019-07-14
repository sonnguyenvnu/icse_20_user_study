@Override public void saveSelfArgs(Bundle args){
  if (currentPicturePath != null) {
    args.putString("path",currentPicturePath);
  }
}
