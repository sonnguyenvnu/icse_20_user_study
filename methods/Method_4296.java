/** 
 * Sets the playback pitch. Calling this method will discard any data buffered within the processor, and may update the value returned by  {@link #isActive()}.
 * @param pitch The requested new pitch.
 * @return The actual new pitch.
 */
public float setPitch(float pitch){
  pitch=Util.constrainValue(pitch,MINIMUM_PITCH,MAXIMUM_PITCH);
  if (this.pitch != pitch) {
    this.pitch=pitch;
    sonic=null;
  }
  flush();
  return pitch;
}
