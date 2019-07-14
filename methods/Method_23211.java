/** 
 * Create default renderer, likely to be resized, but needed for surface init. 
 */
protected PGraphics createPrimaryGraphics(){
  return makeGraphics(sketchWidth(),sketchHeight(),sketchRenderer(),sketchOutputPath(),true);
}
