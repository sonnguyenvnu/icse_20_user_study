/** 
 * Apply the logo to the media player. <p> All previously applied properties will be set on the media player.
 * @param mediaPlayer media player
 */
public void apply(MediaPlayer mediaPlayer){
  if (duration != null) {
    mediaPlayer.logo().setDuration(duration);
  }
  if (intOpacity != null) {
    mediaPlayer.logo().setOpacity(intOpacity);
  }
  if (floatOpacity != null) {
    mediaPlayer.logo().setOpacity(floatOpacity);
  }
  if (x != null && y != null && x >= 0 && y >= 0) {
    mediaPlayer.logo().setLocation(x,y);
  }
  if (position != null) {
    mediaPlayer.logo().setPosition(position);
  }
  if (repeat != null) {
    mediaPlayer.logo().setRepeat(repeat);
  }
  if (!files.isEmpty()) {
    mediaPlayer.logo().setFile(convertFileSpecs());
  }
  if (image != null) {
    mediaPlayer.logo().setImage(image);
  }
  if (enable) {
    mediaPlayer.logo().enable(true);
  }
}
