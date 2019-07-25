/** 
 * Adds the specified value to the statistics for this column.
 * @param columnValue the column value to be profiled
 * @param columnCount the number of rows containing the value
 */
@Override public void accomodate(@Nullable final Object columnValue,@Nonnull Long columnCount){
  accomodateCommon(columnValue,columnCount);
  String stringValue=(columnValue != null) ? columnValue.toString() : null;
  if (!StringUtils.isEmpty(stringValue)) {
    Timestamp timestamp=Timestamp.valueOf(stringValue);
    if (maxTimestamp == null || maxTimestamp.before(timestamp)) {
      maxTimestamp=timestamp;
    }
    if (minTimestamp == null || minTimestamp.after(timestamp)) {
      minTimestamp=timestamp;
    }
  }
}
