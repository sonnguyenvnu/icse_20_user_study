/** 
 * Update this union with a CpcSketch.
 * @param sketch the given CpcSketch.
 */
public void update(final CpcSketch sketch){
  mergeInto(this,sketch);
}
