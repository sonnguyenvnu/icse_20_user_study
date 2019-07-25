/** 
 * Calculate short-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnShortValue=Short.valueOf(String.valueOf(columnValue));
    if (max < columnShortValue) {
      max=columnShortValue;
    }
    if (min > columnShortValue) {
      min=columnShortValue;
    }
    sum+=(columnShortValue * columnCount);
    oldMean=0.0d;
    oldSumOfSquares=0.0d;
    for (int i=1; i <= columnCount; i++) {
      oldMean=mean;
      oldSumOfSquares=sumOfSquares;
      mean=oldMean + ((columnShortValue - oldMean) / (totalCount - columnCount + i - nullCount));
      sumOfSquares=oldSumOfSquares + ((columnShortValue - mean) * (columnShortValue - oldMean));
    }
    variance=sumOfSquares / (totalCount - nullCount);
    stddev=Math.sqrt(variance);
  }
}
