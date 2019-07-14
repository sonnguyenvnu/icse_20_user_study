private void setCurrentView(final int viewIndex){
  long millis=mCalendar.getTimeInMillis();
switch (viewIndex) {
case MONTH_AND_DAY_VIEW:
    ObjectAnimator pulseAnimator=Utils.getPulseAnimator(mMonthAndDayView,0.9f,1.05f);
  if (mDelayAnimation) {
    pulseAnimator.setStartDelay(ANIMATION_DELAY);
    mDelayAnimation=false;
  }
mDayPickerView.onDateChanged();
if (mCurrentView != viewIndex) {
mMonthAndDayView.setSelected(true);
mYearView.setSelected(false);
mAnimator.setDisplayedChild(MONTH_AND_DAY_VIEW);
mCurrentView=viewIndex;
}
pulseAnimator.start();
int flags=DateUtils.FORMAT_SHOW_DATE;
String dayString=DateUtils.formatDateTime(getActivity(),millis,flags);
mAnimator.setContentDescription(mDayPickerDescription + ": " + dayString);
Utils.tryAccessibilityAnnounce(mAnimator,mSelectDay);
break;
case YEAR_VIEW:
pulseAnimator=Utils.getPulseAnimator(mYearView,0.85f,1.1f);
if (mDelayAnimation) {
pulseAnimator.setStartDelay(ANIMATION_DELAY);
mDelayAnimation=false;
}
mYearPickerView.onDateChanged();
if (mCurrentView != viewIndex) {
mMonthAndDayView.setSelected(false);
mYearView.setSelected(true);
mAnimator.setDisplayedChild(YEAR_VIEW);
mCurrentView=viewIndex;
}
pulseAnimator.start();
CharSequence yearString=YEAR_FORMAT.format(millis);
mAnimator.setContentDescription(mYearPickerDescription + ": " + yearString);
Utils.tryAccessibilityAnnounce(mAnimator,mSelectYear);
break;
}
}
