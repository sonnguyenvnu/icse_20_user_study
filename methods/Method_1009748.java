public void initialize(Context context,int initialHoursOfDay,int initialMinutes,boolean is24HourMode){
  if (mTimeInitialized) {
    Log.e(TAG,"Time has already been initialized.");
    return;
  }
  mIs24HourMode=is24HourMode;
  if (is24HourMode) {
    m24HoursGrid=(TwentyFourHoursGrid)inflate(context,R.layout.bsp_pad_24_hours,null);
    m24HoursGrid.initialize(this);
    if (initialHoursOfDay >= 12) {
      m24HoursGrid.swapTexts();
    }
    addView(m24HoursGrid);
  }
 else {
    mHoursGrid=(HoursGrid)inflate(context,R.layout.bsp_pad_12_hours,null);
    mHoursGrid.initialize(this);
    addView(mHoursGrid);
  }
  mMinutesGrid=(MinutesGrid)inflate(context,R.layout.bsp_pad_minutes,null);
  mMinutesGrid.initialize(this);
  addView(mMinutesGrid);
  setInAnimation(mInAnimation);
  setOutAnimation(mOutAnimation);
  setValueForItem(HOUR_INDEX,initialHoursOfDay);
  setValueForItem(MINUTE_INDEX,initialMinutes);
  mTimeInitialized=true;
}
