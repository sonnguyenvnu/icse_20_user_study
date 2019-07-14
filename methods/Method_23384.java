/** 
 * Record individual lines and triangles by echoing them to another renderer.
 */
public void beginRaw(PGraphics rawGraphics){
  this.raw=rawGraphics;
  rawGraphics.beginDraw();
}
