/** 
 * If the source is currently having difficulty providing chunks, then this method throws the underlying error. Otherwise does nothing.
 * @throws IOException The underlying error.
 */
public void maybeThrowError() throws IOException {
  if (fatalError != null) {
    throw fatalError;
  }
  if (expectedPlaylistUrl != null && seenExpectedPlaylistError) {
    playlistTracker.maybeThrowPlaylistRefreshError(expectedPlaylistUrl);
  }
}
