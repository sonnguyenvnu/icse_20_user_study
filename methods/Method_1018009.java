/** 
 * Calculate date-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnDateValue=Date.valueOf(String.valueOf(columnValue));
  }
  if (columnDateValue != null) {
    if (maxDate.before(columnDateValue)) {
      maxDate=columnDateValue;
    }
    if (minDate.after(columnDateValue)) {
      minDate=columnDateValue;
    }
  }
}
