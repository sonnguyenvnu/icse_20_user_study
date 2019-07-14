/** 
 * @return the full key for aggregation of QA warnings
 */
@JsonIgnore public String getAggregationId(){
  if (this.bucketId != null) {
    return this.type + "_" + this.bucketId;
  }
 else {
    return this.type;
  }
}
