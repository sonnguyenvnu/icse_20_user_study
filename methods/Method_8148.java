public void setMultilineDetail(boolean value){
  multiline=value;
  if (value) {
    valueTextView.setLines(0);
    valueTextView.setMaxLines(0);
    valueTextView.setSingleLine(false);
    valueTextView.setPadding(0,0,0,AndroidUtilities.dp(12));
  }
 else {
    valueTextView.setLines(1);
    valueTextView.setMaxLines(1);
    valueTextView.setSingleLine(true);
    valueTextView.setPadding(0,0,0,0);
  }
}
