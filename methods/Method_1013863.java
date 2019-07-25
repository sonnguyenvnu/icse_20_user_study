/** 
 * Measures time since last call and calculates FPS based on the time interval and window width.
 * @return current FPS;
 */
public float measure(){
  final long time=System.currentTimeMillis();
  this.interval=time - this.lastTime;
  this.fps+=((1000.0f / Math.max(this.interval,1)) - this.fps) / this.width;
  this.lastTime=time;
  return this.fps;
}
