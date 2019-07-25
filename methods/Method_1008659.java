/** 
 * Sets the format to use on the output of this aggregation.
 */
public BucketScriptPipelineAggregationBuilder format(String format){
  if (format == null) {
    throw new IllegalArgumentException("[format] must not be null: [" + name + "]");
  }
  this.format=format;
  return this;
}
