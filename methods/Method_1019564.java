/** 
 * Updates the internal set by adding entries from the given sketch
 * @param sketchIn input sketch to add to the internal set
 */
public void update(final Sketch<S> sketchIn){
  if (sketchIn == null || sketchIn.isEmpty()) {
    return;
  }
  if (sketchIn.theta_ < theta_) {
    theta_=sketchIn.theta_;
  }
  final SketchIterator<S> it=sketchIn.iterator();
  while (it.next()) {
    sketch_.merge(it.getKey(),it.getSummary(),summarySetOps_);
  }
}
