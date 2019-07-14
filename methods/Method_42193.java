/** 
 * Get the list of Selected Media.
 * @return A list containing the selected Media items.
 */
public List<Media> getSelectedMedia(){
  List<Media> selectedMedia=new ArrayList<>();
  for (  int selectedPos : selectedPositions) {
    selectedMedia.add((Media)timelineItems.get(selectedPos));
  }
  return selectedMedia;
}
