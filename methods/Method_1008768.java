public void initialize(OnTimeSetListener callback,int hourOfDay,int minute,int hourOfDayEnd,int minuteEnd,boolean is24HourMode){
  mCallback=callback;
  mInitialHourOfDay=hourOfDay;
  mInitialMinute=minute;
  mInitialHourOfDayEnd=hourOfDayEnd;
  mInitialMinuteEnd=minuteEnd;
  mIs24HourMode=is24HourMode;
  mInKbMode=false;
  mTitle="";
  mThemeDark=false;
  mAccentColor=-1;
  mVibrate=true;
  mDismissOnPause=false;
}
