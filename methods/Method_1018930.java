/** 
 * Apply the marquee to the media player.
 * @param mediaPlayer media player
 */
public void apply(MediaPlayer mediaPlayer){
  if (text != null) {
    mediaPlayer.marquee().setText(text);
  }
  if (colour != null) {
    mediaPlayer.marquee().setColour(colour);
  }
  if (rgb != null) {
    mediaPlayer.marquee().setColour(rgb);
  }
  if (intOpacity != null) {
    mediaPlayer.marquee().setOpacity(intOpacity);
  }
  if (floatOpacity != null) {
    mediaPlayer.marquee().setOpacity(floatOpacity);
  }
  if (size != null) {
    mediaPlayer.marquee().setSize(size);
  }
  if (timeout != null) {
    mediaPlayer.marquee().setTimeout(timeout);
  }
  if (x != null && y != null && x >= 0 && y >= 0) {
    mediaPlayer.marquee().setLocation(x,y);
  }
  if (position != null) {
    mediaPlayer.marquee().setPosition(position);
  }
  if (refresh != null) {
    mediaPlayer.marquee().setRefresh(refresh);
  }
  if (enable) {
    mediaPlayer.marquee().enable(true);
  }
}
