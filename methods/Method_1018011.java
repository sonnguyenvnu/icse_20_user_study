/** 
 * Calculate double-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnDoubleValue=Double.valueOf(String.valueOf(columnValue));
    if (max < columnDoubleValue) {
      max=columnDoubleValue;
    }
    if (min > columnDoubleValue) {
      min=columnDoubleValue;
    }
    sum+=(columnDoubleValue * columnCount);
    oldMean=0.0d;
    oldSumOfSquares=0.0d;
    for (int i=1; i <= columnCount; i++) {
      oldMean=mean;
      oldSumOfSquares=sumOfSquares;
      mean=oldMean + ((columnDoubleValue - oldMean) / (totalCount - columnCount + i - nullCount));
      sumOfSquares=oldSumOfSquares + ((columnDoubleValue - mean) * (columnDoubleValue - oldMean));
    }
    variance=sumOfSquares / (totalCount - nullCount);
    stddev=Math.sqrt(variance);
  }
}
