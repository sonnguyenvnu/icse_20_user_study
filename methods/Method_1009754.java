private void initialize(OnTimeSetListener callback,boolean set24HourModeAtRuntime,boolean is24HourMode){
  mTimeSetListener=callback;
  mThemeDark=false;
  mThemeSetAtRuntime=false;
  mSet24HourModeAtRuntime=set24HourModeAtRuntime;
  if (set24HourModeAtRuntime) {
    mIs24HourMode=is24HourMode;
  }
}
