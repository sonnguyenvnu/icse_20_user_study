/** 
 * Writes an already encoded sample from an input stream into a track. <p> This method does not inspect the contents of the samples. The contents has to match the format and dimensions of the media in this track.
 * @param track The track index.
 * @param in The input stream which holds the encoded sample data.
 * @param duration The duration of the video frame in media time scale units.
 * @param isSync Whether the sample is a sync sample (keyframe).
 * @throws IllegalArgumentException if the duration is less than 1.
 * @throws IOException if writing the image failed.
 */
public void writeSample(int track,InputStream in,long duration,boolean isSync) throws IOException {
  ensureStarted();
  if (duration <= 0) {
    throw new IllegalArgumentException("duration must be greater 0");
  }
  Track t=tracks.get(track);
  ensureOpen();
  ensureStarted();
  long offset=getRelativeStreamPosition();
  OutputStream mdatOut=mdatAtom.getOutputStream();
  byte[] buf=new byte[4096];
  int len;
  while ((len=in.read(buf)) != -1) {
    mdatOut.write(buf,0,len);
  }
  long length=getRelativeStreamPosition() - offset;
  t.addSample(new Sample(duration,offset,length),1,isSync);
}
