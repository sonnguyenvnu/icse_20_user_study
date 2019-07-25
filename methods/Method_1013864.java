/** 
 * Clears all the collected data.
 */
public void flush(){
  this.lastTime=System.currentTimeMillis();
  this.fps=0.0f;
  this.interval=0;
}
