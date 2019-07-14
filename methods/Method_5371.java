/** 
 * Prepares the sample stream wrapper with master playlist information.
 * @param trackGroups The {@link TrackGroupArray} to expose.
 * @param primaryTrackGroupIndex The index of the adaptive track group.
 * @param optionalTrackGroups A subset of {@code trackGroups} that should not trigger a failure ifnot found in the media playlist's segments.
 */
public void prepareWithMasterPlaylistInfo(TrackGroupArray trackGroups,int primaryTrackGroupIndex,TrackGroupArray optionalTrackGroups){
  prepared=true;
  this.trackGroups=trackGroups;
  this.optionalTrackGroups=optionalTrackGroups;
  this.primaryTrackGroupIndex=primaryTrackGroupIndex;
  callback.onPrepared();
}
