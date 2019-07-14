public void setCustomDate(int date){
  if (customDate == date) {
    return;
  }
  CharSequence newText=LocaleController.formatDateChat(date);
  if (customText != null && TextUtils.equals(newText,customText)) {
    return;
  }
  customDate=date;
  customText=newText;
  if (getMeasuredWidth() != 0) {
    createLayout(customText,getMeasuredWidth());
    invalidate();
  }
  if (!wasLayout) {
    AndroidUtilities.runOnUIThread(this::requestLayout);
  }
 else {
    buildLayout();
  }
}
