/** 
 * Private method called from constructor.
 */
private int[] setPeriodInternal(int years,int months,int weeks,int days,int hours,int minutes,int seconds,int millis){
  int[] newValues=new int[size()];
  checkAndUpdate(DurationFieldType.years(),newValues,years);
  checkAndUpdate(DurationFieldType.months(),newValues,months);
  checkAndUpdate(DurationFieldType.weeks(),newValues,weeks);
  checkAndUpdate(DurationFieldType.days(),newValues,days);
  checkAndUpdate(DurationFieldType.hours(),newValues,hours);
  checkAndUpdate(DurationFieldType.minutes(),newValues,minutes);
  checkAndUpdate(DurationFieldType.seconds(),newValues,seconds);
  checkAndUpdate(DurationFieldType.millis(),newValues,millis);
  return newValues;
}
