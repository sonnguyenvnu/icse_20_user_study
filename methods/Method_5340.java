/** 
 * Attempts to blacklist the track associated with the given chunk. Blacklisting will fail if the track is the only non-blacklisted track in the selection.
 * @param chunk The chunk whose load caused the blacklisting attempt.
 * @param blacklistDurationMs The number of milliseconds for which the track selection should beblacklisted.
 * @return Whether the blacklisting succeeded.
 */
public boolean maybeBlacklistTrack(Chunk chunk,long blacklistDurationMs){
  return trackSelection.blacklist(trackSelection.indexOf(trackGroup.indexOf(chunk.trackFormat)),blacklistDurationMs);
}
