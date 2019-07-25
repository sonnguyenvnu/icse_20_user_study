/** 
 * Update this union operator with the given sketch.
 * @param sketch the given sketch.
 */
public void update(final HllSketch sketch){
  gadget.hllSketchImpl=unionImpl(sketch.hllSketchImpl,gadget.hllSketchImpl,lgMaxK);
}
