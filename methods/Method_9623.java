@Override public void restoreSelfArgs(Bundle args){
  if (imageUpdater != null) {
    imageUpdater.currentPicturePath=args.getString("path");
  }
}
