/** 
 * @nowebref Begin recording (echoing) commands to the specified PGraphics object.
 */
public void beginRecord(PGraphics recorder){
  this.recorder=recorder;
  recorder.beginDraw();
}
