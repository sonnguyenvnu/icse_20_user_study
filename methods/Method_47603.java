private void updateAmPmDisplay(int amOrPm){
  if (amOrPm == AM) {
    mAmPmTextView.setText(mAmText);
    Utils.tryAccessibilityAnnounce(mTimePicker,mAmText);
    mAmPmHitspace.setContentDescription(mAmText);
  }
 else   if (amOrPm == PM) {
    mAmPmTextView.setText(mPmText);
    Utils.tryAccessibilityAnnounce(mTimePicker,mPmText);
    mAmPmHitspace.setContentDescription(mPmText);
  }
 else {
    mAmPmTextView.setText(mDoublePlaceholderText);
  }
}
