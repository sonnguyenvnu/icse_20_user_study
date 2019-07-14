/** 
 * Sets the edit list for the specified track. <p> In the absence of an edit list, the presentation of the track starts immediately. An empty edit is used to offset the start time of a track. <p>
 * @throws IllegalArgumentException If the edit list ends with an empty edit.
 */
public void setEditList(int track,Edit[] editList){
  if (editList != null && editList.length > 0 && editList[editList.length - 1].mediaTime == -1) {
    throw new IllegalArgumentException("Edit list must not end with empty edit.");
  }
  tracks.get(track).editList=editList;
}
