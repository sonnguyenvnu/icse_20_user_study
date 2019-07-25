/** 
 * Combine with another column statistics
 */
@Override public void combine(StandardColumnStatistics v_columnStatistics){
  combineCommon(v_columnStatistics);
  StringColumnStatistics vString_columnStatistics=(StringColumnStatistics)v_columnStatistics;
  maxLength=Math.max(maxLength,vString_columnStatistics.maxLength);
  if ((minLength != Integer.MAX_VALUE) && (vString_columnStatistics.minLength != Integer.MAX_VALUE)) {
    if (minLength > vString_columnStatistics.minLength) {
      minLength=vString_columnStatistics.minLength;
      shortestString=vString_columnStatistics.shortestString;
    }
  }
 else   if (minLength == Integer.MAX_VALUE) {
    minLength=vString_columnStatistics.minLength;
    shortestString=vString_columnStatistics.shortestString;
  }
 else {
    minLength=0;
  }
  if ((initializationFlag) && (vString_columnStatistics.initializationFlag)) {
    if (minStringCase.compareTo(vString_columnStatistics.minStringCase) > 0) {
      minStringCase=vString_columnStatistics.minStringCase;
    }
    if (maxStringCase.compareTo(vString_columnStatistics.maxStringCase) < 0) {
      maxStringCase=vString_columnStatistics.maxStringCase;
    }
    if (minStringICase.compareToIgnoreCase(vString_columnStatistics.minStringICase) > 0) {
      minStringICase=vString_columnStatistics.minStringICase;
    }
    if (maxStringICase.compareToIgnoreCase(vString_columnStatistics.maxStringICase) < 0) {
      maxStringICase=vString_columnStatistics.maxStringICase;
    }
  }
 else   if (!initializationFlag) {
    minStringCase=vString_columnStatistics.minStringCase;
    maxStringCase=vString_columnStatistics.maxStringCase;
    minStringICase=vString_columnStatistics.minStringICase;
    maxStringICase=vString_columnStatistics.maxStringICase;
  }
  if (longestString.length() < vString_columnStatistics.longestString.length()) {
    longestString=vString_columnStatistics.longestString;
  }
  emptyCount+=vString_columnStatistics.emptyCount;
  doPercentageCalculations();
}
