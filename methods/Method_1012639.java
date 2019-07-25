/** 
 * Match media information to audio codecs supported by the renderer and return its MIME-type if the match is successful. Returns null if the media is not natively supported by the renderer, which means it has to be transcoded.
 * @param media The MediaInfo metadata
 * @return The MIME type or null if no match was found.
 */
public String match(DLNAMediaInfo media){
  if (media == null) {
    return null;
  }
  int frameRate=0;
  if (isNotBlank(media.getFrameRate())) {
    try {
      frameRate=(int)Math.round(Double.parseDouble(media.getFrameRate()));
    }
 catch (    NumberFormatException e) {
      LOGGER.debug("Could not parse framerate \"{}\" for media {}: {}",media.getFrameRate(),media,e.getMessage());
      LOGGER.trace("",e);
    }
  }
  if (media.getFirstAudioTrack() == null) {
    return match(media.getContainer(),media.getCodecV(),null,0,0,media.getBitrate(),frameRate,media.getWidth(),media.getHeight(),media.getExtras());
  }
  if (media.isSLS()) {
    DLNAMediaAudio audio=media.getFirstAudioTrack();
    return match(media.getContainer(),media.getCodecV(),audio.getCodecA(),audio.getAudioProperties().getNumberOfChannels(),audio.getSampleRate(),audio.getBitRate(),frameRate,media.getWidth(),media.getHeight(),media.getExtras());
  }
  String finalMimeType=null;
  for (  DLNAMediaAudio audio : media.getAudioTracksList()) {
    String mimeType=match(media.getContainer(),media.getCodecV(),audio.getCodecA(),audio.getAudioProperties().getNumberOfChannels(),audio.getSampleRate(),media.getBitrate(),frameRate,media.getWidth(),media.getHeight(),media.getExtras());
    finalMimeType=mimeType;
    if (mimeType == null) {
      return null;
    }
  }
  return finalMimeType;
}
