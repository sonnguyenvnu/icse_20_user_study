/** 
 * {@inheritDoc}
 */
@Override protected void interpolate(double d){
  timeline.get().playFrom(Duration.seconds(d));
  timeline.get().stop();
}
