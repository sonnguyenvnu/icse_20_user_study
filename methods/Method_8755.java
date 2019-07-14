private void postChangeCurrentByOneFromLongPress(boolean increment,long delayMillis){
  if (mChangeCurrentByOneFromLongPressCommand == null) {
    mChangeCurrentByOneFromLongPressCommand=new ChangeCurrentByOneFromLongPressCommand();
  }
 else {
    removeCallbacks(mChangeCurrentByOneFromLongPressCommand);
  }
  mChangeCurrentByOneFromLongPressCommand.setStep(increment);
  postDelayed(mChangeCurrentByOneFromLongPressCommand,delayMillis);
}
