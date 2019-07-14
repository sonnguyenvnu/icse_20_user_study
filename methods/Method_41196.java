/** 
 * Sets the time range for the <CODE>DailyCalendar</CODE> to the times represented in the specified <CODE>java.util.Calendar</CODE>s. 
 * @param rangeStartingCalendar a Calendar containing the start time forthe <CODE>DailyCalendar</CODE>
 * @param rangeEndingCalendar   a Calendar containing the end time forthe <CODE>DailyCalendar</CODE>
 */
public void setTimeRange(Calendar rangeStartingCalendar,Calendar rangeEndingCalendar){
  setTimeRange(rangeStartingCalendar.get(Calendar.HOUR_OF_DAY),rangeStartingCalendar.get(Calendar.MINUTE),rangeStartingCalendar.get(Calendar.SECOND),rangeStartingCalendar.get(Calendar.MILLISECOND),rangeEndingCalendar.get(Calendar.HOUR_OF_DAY),rangeEndingCalendar.get(Calendar.MINUTE),rangeEndingCalendar.get(Calendar.SECOND),rangeEndingCalendar.get(Calendar.MILLISECOND));
}
