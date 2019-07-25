/** 
 * Calculate float-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnFloatValue=Float.valueOf(String.valueOf(columnValue));
    if (max < columnFloatValue) {
      max=columnFloatValue;
    }
    if (min > columnFloatValue) {
      min=columnFloatValue;
    }
    sum+=(columnFloatValue * columnCount);
    oldMean=0.0d;
    oldSumOfSquares=0.0d;
    for (int i=1; i <= columnCount; i++) {
      oldMean=mean;
      oldSumOfSquares=sumOfSquares;
      mean=oldMean + ((columnFloatValue - oldMean) / (totalCount - columnCount + i - nullCount));
      sumOfSquares=oldSumOfSquares + ((columnFloatValue - mean) * (columnFloatValue - oldMean));
    }
    variance=sumOfSquares / (totalCount - nullCount);
    stddev=Math.sqrt(variance);
  }
}
