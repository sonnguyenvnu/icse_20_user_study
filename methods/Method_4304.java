/** 
 * Sets the number of audio frames to trim from the start and end of audio passed to this processor. After calling this method, call  {@link #configure(int,int,int)} to apply the newtrimming frame counts.
 * @param trimStartFrames The number of audio frames to trim from the start of audio.
 * @param trimEndFrames The number of audio frames to trim from the end of audio.
 * @see AudioSink#configure(int,int,int,int,int[],int,int)
 */
public void setTrimFrameCount(int trimStartFrames,int trimEndFrames){
  this.trimStartFrames=trimStartFrames;
  this.trimEndFrames=trimEndFrames;
}
