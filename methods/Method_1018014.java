/** 
 * Calculate string-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnStringValue=String.valueOf(columnValue);
    columnStringLength=columnStringValue.length();
    if (maxLength < columnStringLength) {
      maxLength=columnStringLength;
      longestString=columnStringValue;
    }
    if (!columnStringValue.isEmpty()) {
      if (minLength > columnStringLength) {
        minLength=columnStringLength;
        shortestString=columnStringValue;
      }
      if (!initializationFlag) {
        minStringCase=columnStringValue;
        maxStringCase=columnStringValue;
        minStringICase=columnStringValue;
        maxStringICase=columnStringValue;
        initializationFlag=true;
      }
 else {
        if (minStringCase.compareTo(columnStringValue) > 0) {
          minStringCase=columnStringValue;
        }
        if (maxStringCase.compareTo(columnStringValue) < 0) {
          maxStringCase=columnStringValue;
        }
        if (minStringICase.compareToIgnoreCase(columnStringValue) > 0) {
          minStringICase=columnStringValue;
        }
        if (maxStringICase.compareToIgnoreCase(columnStringValue) < 0) {
          maxStringICase=columnStringValue;
        }
      }
    }
    if (columnStringValue.isEmpty()) {
      emptyCount+=columnCount;
    }
    doPercentageCalculations();
  }
}
