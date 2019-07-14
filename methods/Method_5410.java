/** 
 * Called by the bundles when a snapshot changes.
 * @param url The url of the playlist.
 * @param newSnapshot The new snapshot.
 */
private void onPlaylistUpdated(HlsUrl url,HlsMediaPlaylist newSnapshot){
  if (url == primaryHlsUrl) {
    if (primaryUrlSnapshot == null) {
      isLive=!newSnapshot.hasEndTag;
      initialStartTimeUs=newSnapshot.startTimeUs;
    }
    primaryUrlSnapshot=newSnapshot;
    primaryPlaylistListener.onPrimaryPlaylistRefreshed(newSnapshot);
  }
  int listenersSize=listeners.size();
  for (int i=0; i < listenersSize; i++) {
    listeners.get(i).onPlaylistChanged();
  }
}
