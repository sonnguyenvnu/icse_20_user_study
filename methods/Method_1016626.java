/** 
 * Add a single Point to these batches.
 * @param point the Point to add
 * @return this Instance to be able to daisy chain calls.
 */
public BatchPoints point(final Point point){
  point.getTags().putAll(this.tags);
  this.points.add(point);
  return this;
}
