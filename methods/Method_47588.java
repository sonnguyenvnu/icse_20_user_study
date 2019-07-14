/** 
 * Initialize the Layout with starting values.
 * @param context
 * @param initialHoursOfDay
 * @param initialMinutes
 * @param is24HourMode
 */
public void initialize(Context context,HapticFeedbackController hapticFeedbackController,int initialHoursOfDay,int initialMinutes,boolean is24HourMode){
  if (mTimeInitialized) {
    Log.e(TAG,"Time has already been initialized.");
    return;
  }
  mHapticFeedbackController=hapticFeedbackController;
  mIs24HourMode=is24HourMode;
  mHideAmPm=mAccessibilityManager.isTouchExplorationEnabled() ? true : mIs24HourMode;
  mCircleView.initialize(context,mHideAmPm);
  mCircleView.invalidate();
  if (!mHideAmPm) {
    mAmPmCirclesView.initialize(context,initialHoursOfDay < 12 ? AM : PM);
    mAmPmCirclesView.invalidate();
  }
  Resources res=context.getResources();
  int[] hours={12,1,2,3,4,5,6,7,8,9,10,11};
  int[] hours_24={0,13,14,15,16,17,18,19,20,21,22,23};
  int[] minutes={0,5,10,15,20,25,30,35,40,45,50,55};
  String[] hoursTexts=new String[12];
  String[] innerHoursTexts=new String[12];
  String[] minutesTexts=new String[12];
  for (int i=0; i < 12; i++) {
    hoursTexts[i]=is24HourMode ? String.format("%02d",hours_24[i]) : String.format("%d",hours[i]);
    innerHoursTexts[i]=String.format("%d",hours[i]);
    minutesTexts[i]=String.format("%02d",minutes[i]);
  }
  mHourRadialTextsView.initialize(res,hoursTexts,(is24HourMode ? innerHoursTexts : null),mHideAmPm,true);
  mHourRadialTextsView.invalidate();
  mMinuteRadialTextsView.initialize(res,minutesTexts,null,mHideAmPm,false);
  mMinuteRadialTextsView.invalidate();
  setValueForItem(HOUR_INDEX,initialHoursOfDay);
  setValueForItem(MINUTE_INDEX,initialMinutes);
  int hourDegrees=(initialHoursOfDay % 12) * HOUR_VALUE_TO_DEGREES_STEP_SIZE;
  mHourRadialSelectorView.initialize(context,mHideAmPm,is24HourMode,true,hourDegrees,isHourInnerCircle(initialHoursOfDay));
  int minuteDegrees=initialMinutes * MINUTE_VALUE_TO_DEGREES_STEP_SIZE;
  mMinuteRadialSelectorView.initialize(context,mHideAmPm,false,false,minuteDegrees,false);
  mTimeInitialized=true;
}
