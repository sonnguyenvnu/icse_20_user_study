public void initialize(OnDateSetListener callBack,int year,int monthOfYear,int dayOfMonth,int yearEnd,int montOfYearEnd,int dayOfMonthEnd){
  mCallBack=callBack;
  mCalendar.set(Calendar.YEAR,year);
  mCalendar.set(Calendar.MONTH,monthOfYear);
  mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
  mCalendarEnd.set(Calendar.YEAR,yearEnd);
  mCalendarEnd.set(Calendar.MONTH,montOfYearEnd);
  mCalendarEnd.set(Calendar.DAY_OF_MONTH,dayOfMonthEnd);
  mThemeDark=false;
  mAccentColor=-1;
  mVibrate=true;
  mDismissOnPause=false;
}
