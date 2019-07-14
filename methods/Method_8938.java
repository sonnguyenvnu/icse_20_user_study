public void updateSelectedCount(int count,boolean disable){
  if (count == 0) {
    doneButtonBadgeTextView.setVisibility(View.GONE);
    if (disable) {
      doneButtonTextView.setTag(isDarkTheme ? null : Theme.key_picker_disabledButton);
      doneButtonTextView.setTextColor(isDarkTheme ? 0xff999999 : Theme.getColor(Theme.key_picker_disabledButton));
      doneButton.setEnabled(false);
    }
 else {
      doneButtonTextView.setTag(isDarkTheme ? null : Theme.key_picker_enabledButton);
      doneButtonTextView.setTextColor(isDarkTheme ? 0xffffffff : Theme.getColor(Theme.key_picker_enabledButton));
    }
  }
 else {
    doneButtonBadgeTextView.setVisibility(View.VISIBLE);
    doneButtonBadgeTextView.setText(String.format("%d",count));
    doneButtonTextView.setTag(isDarkTheme ? null : Theme.key_picker_enabledButton);
    doneButtonTextView.setTextColor(isDarkTheme ? 0xffffffff : Theme.getColor(Theme.key_picker_enabledButton));
    if (disable) {
      doneButton.setEnabled(true);
    }
  }
}
