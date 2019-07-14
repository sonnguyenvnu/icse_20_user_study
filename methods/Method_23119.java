/** 
 * Sets the compression quality of a  track. <p> A value of 0 stands for "high compression is important" a value of 1 for "high image quality is important". <p> Changing this value affects the encoding of video frames which are subsequently written into the track. Frames which have already been written are not changed. <p> This value has no effect on videos encoded with lossless encoders such as the PNG format. <p> The default value is 0.97.
 * @param newValue
 */
public void setCompressionQuality(int track,float newValue){
  ((VideoTrack)tracks.get(track)).videoQuality=newValue;
}
