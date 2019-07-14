/** 
 * Sets the playback speed. Calling this method will discard any data buffered within the processor, and may update the value returned by  {@link #isActive()}.
 * @param speed The requested new playback speed.
 * @return The actual new playback speed.
 */
public float setSpeed(float speed){
  speed=Util.constrainValue(speed,MINIMUM_SPEED,MAXIMUM_SPEED);
  if (this.speed != speed) {
    this.speed=speed;
    sonic=null;
  }
  flush();
  return speed;
}
