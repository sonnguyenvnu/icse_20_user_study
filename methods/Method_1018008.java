/** 
 * Calculate byte-specific statistics by accommodating the value and frequency/count
 */
@Override public void accomodate(Object columnValue,Long columnCount){
  accomodateCommon(columnValue,columnCount);
  if (columnValue != null) {
    columnByteValue=Byte.valueOf(String.valueOf(columnValue));
    if (max < columnByteValue) {
      max=columnByteValue;
    }
    if (min > columnByteValue) {
      min=columnByteValue;
    }
    sum+=(columnByteValue * columnCount);
    oldMean=0.0d;
    oldSumOfSquares=0.0d;
    for (int i=1; i <= columnCount; i++) {
      oldMean=mean;
      oldSumOfSquares=sumOfSquares;
      mean=oldMean + ((columnByteValue - oldMean) / (totalCount - columnCount + i - nullCount));
      sumOfSquares=oldSumOfSquares + ((columnByteValue - mean) * (columnByteValue - oldMean));
    }
    variance=sumOfSquares / (totalCount - nullCount);
    stddev=Math.sqrt(variance);
  }
}
