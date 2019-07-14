@Override public void restoreSelfArgs(Bundle args){
  if (currentStep == 0) {
    if (imageUpdater != null) {
      imageUpdater.currentPicturePath=args.getString("path");
    }
    String text=args.getString("nameTextView");
    if (text != null) {
      if (nameTextView != null) {
        nameTextView.setText(text);
      }
 else {
        nameToSet=text;
      }
    }
  }
}
