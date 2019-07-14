/** 
 * Derives a track sample format from the corresponding format in the master playlist, and a sample format that may have been obtained from a chunk belonging to a different track.
 * @param playlistFormat The format information obtained from the master playlist.
 * @param sampleFormat The format information obtained from the samples.
 * @param propagateBitrate Whether the bitrate from the playlist format should be included in thederived format.
 * @return The derived track format.
 */
private static Format deriveFormat(Format playlistFormat,Format sampleFormat,boolean propagateBitrate){
  if (playlistFormat == null) {
    return sampleFormat;
  }
  int bitrate=propagateBitrate ? playlistFormat.bitrate : Format.NO_VALUE;
  int sampleTrackType=MimeTypes.getTrackType(sampleFormat.sampleMimeType);
  String codecs=Util.getCodecsOfType(playlistFormat.codecs,sampleTrackType);
  String mimeType=MimeTypes.getMediaMimeType(codecs);
  if (mimeType == null) {
    mimeType=sampleFormat.sampleMimeType;
  }
  return sampleFormat.copyWithContainerInfo(playlistFormat.id,playlistFormat.label,mimeType,codecs,bitrate,playlistFormat.width,playlistFormat.height,playlistFormat.selectionFlags,playlistFormat.language);
}
