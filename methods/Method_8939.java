public void updateSelectedCount(int count,boolean disable){
  if (count == 0) {
    doneButtonBadgeTextView.setVisibility(View.GONE);
    if (disable) {
      doneButton.setTextColor(0xff999999);
      doneButton.setEnabled(false);
    }
 else {
      doneButton.setTextColor(isDarkTheme ? 0xffffffff : 0xff19a7e8);
    }
  }
 else {
    doneButtonBadgeTextView.setVisibility(View.VISIBLE);
    doneButtonBadgeTextView.setText(String.format("%d",count));
    doneButton.setTextColor(isDarkTheme ? 0xffffffff : 0xff19a7e8);
    if (disable) {
      doneButton.setEnabled(true);
    }
  }
}
