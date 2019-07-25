/** 
 * Combine with another column statistics
 */
@Override public void combine(StandardColumnStatistics v_columnStatistics){
  combineCommon(v_columnStatistics);
  DateColumnStatistics vDate_columnStatistics=(DateColumnStatistics)v_columnStatistics;
  if (maxDate.before(vDate_columnStatistics.maxDate)) {
    maxDate=vDate_columnStatistics.maxDate;
  }
  if (minDate.after(vDate_columnStatistics.minDate)) {
    minDate=vDate_columnStatistics.minDate;
  }
}
