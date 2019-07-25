/** 
 * Filters elements on the provided  {@link Sketch}
 * @param sketchIn The sketch against which apply the {@link Predicate}
 * @return A new Sketch with some of the entries filtered out based on the {@link Predicate}
 */
@SuppressWarnings("unchecked") public CompactSketch<T> filter(final Sketch<T> sketchIn){
  if (sketchIn == null) {
    return new CompactSketch<>(null,null,Long.MAX_VALUE,true);
  }
  final QuickSelectSketch<T> sketch=new QuickSelectSketch<>(sketchIn.getRetainedEntries(),ResizeFactor.X1.lg(),null);
  final SketchIterator<T> it=sketchIn.iterator();
  while (it.next()) {
    final T summary=it.getSummary();
    if (predicate.test(summary)) {
      sketch.insert(it.getKey(),(T)summary.copy());
    }
  }
  sketch.setThetaLong(sketchIn.getThetaLong());
  if (!sketchIn.isEmpty()) {
    sketch.setNotEmpty();
  }
  return sketch.compact();
}
