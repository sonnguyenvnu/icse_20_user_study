@Override public void restoreSelfArgs(Bundle args){
  if (imageUpdater != null) {
    imageUpdater.currentPicturePath=args.getString("path");
  }
  String text=args.getString("nameTextView");
  if (text != null) {
    if (editText != null) {
      editText.setText(text);
    }
 else {
      nameToSet=text;
    }
  }
}
