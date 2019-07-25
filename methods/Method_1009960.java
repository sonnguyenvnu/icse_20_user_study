/** 
 * Computes an interpolated value of a quantile that is between two centroids. Index is the quantile desired multiplied by the total number of samples - 1.
 * @param index              Denormalized quantile desired
 * @param previousIndex      The denormalized quantile corresponding to the center of the previous centroid.
 * @param nextIndex          The denormalized quantile corresponding to the center of the following centroid.
 * @param previousMean       The mean of the previous centroid.
 * @param nextMean           The mean of the following centroid.
 * @return  The interpolated mean.
 */
static double quantile(double index,double previousIndex,double nextIndex,double previousMean,double nextMean){
  final double delta=nextIndex - previousIndex;
  final double previousWeight=(nextIndex - index) / delta;
  final double nextWeight=(index - previousIndex) / delta;
  return previousMean * previousWeight + nextMean * nextWeight;
}
