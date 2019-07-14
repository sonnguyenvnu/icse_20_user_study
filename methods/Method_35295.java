private void setTextView(){
  if (tvSelection != null) {
    if (!TextUtils.isEmpty(selectedText)) {
      tvSelection.setText(selectedText);
    }
 else {
      tvSelection.setText("Press pick title to set this title, or pick image to fill in the image view.");
    }
  }
}
