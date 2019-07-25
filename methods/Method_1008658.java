/** 
 * Set the percentages to calculate percentiles for in this aggregation
 */
public PercentilesBucketPipelineAggregationBuilder percents(double[] percents){
  if (percents == null) {
    throw new IllegalArgumentException("[percents] must not be null: [" + name + "]");
  }
  for (  Double p : percents) {
    if (p == null || p < 0.0 || p > 100.0) {
      throw new IllegalArgumentException(PERCENTS_FIELD.getPreferredName() + " must only contain non-null doubles from 0.0-100.0 inclusive");
    }
  }
  this.percents=percents;
  return this;
}
