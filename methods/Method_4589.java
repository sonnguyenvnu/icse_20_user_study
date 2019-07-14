/** 
 * Updates the position of the holder to Cues element's position if the extractor configuration permits use of master seek entry. After building Cues sets the holder's position back to where it was before.
 * @param seekPosition The holder whose position will be updated.
 * @param currentPosition Current position of the input.
 * @return Whether the seek position was updated.
 */
private boolean maybeSeekForCues(PositionHolder seekPosition,long currentPosition){
  if (seekForCues) {
    seekPositionAfterBuildingCues=currentPosition;
    seekPosition.position=cuesContentPosition;
    seekForCues=false;
    return true;
  }
  if (sentSeekMap && seekPositionAfterBuildingCues != C.POSITION_UNSET) {
    seekPosition.position=seekPositionAfterBuildingCues;
    seekPositionAfterBuildingCues=C.POSITION_UNSET;
    return true;
  }
  return false;
}
