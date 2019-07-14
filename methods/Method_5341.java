/** 
 * Called when a playlist load encounters an error.
 * @param url The url of the playlist whose load encountered an error.
 * @param blacklistDurationMs The duration for which the playlist should be blacklisted. Or {@link C#TIME_UNSET} if the playlist should not be blacklisted.
 * @return True if blacklisting did not encounter errors. False otherwise.
 */
public boolean onPlaylistError(HlsUrl url,long blacklistDurationMs){
  int trackGroupIndex=trackGroup.indexOf(url.format);
  if (trackGroupIndex == C.INDEX_UNSET) {
    return true;
  }
  int trackSelectionIndex=trackSelection.indexOf(trackGroupIndex);
  if (trackSelectionIndex == C.INDEX_UNSET) {
    return true;
  }
  seenExpectedPlaylistError|=expectedPlaylistUrl == url;
  return blacklistDurationMs == C.TIME_UNSET || trackSelection.blacklist(trackSelectionIndex,blacklistDurationMs);
}
