public void initialize(OnTimeSetListener callback,int hourOfDay,int minute,boolean is24HourMode){
  mCallback=callback;
  mInitialHourOfDay=hourOfDay;
  mInitialMinute=minute;
  mIs24HourMode=is24HourMode;
  mInKbMode=false;
  mThemeDark=false;
}
