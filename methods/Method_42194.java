/** 
 * Select all elements within the Timeline view.
 */
public void selectAll(){
  int timelineItemSize=timelineItems.size();
  for (int pos=0; pos < timelineItemSize; pos++) {
    TimelineItem timelineItem=getItem(pos);
    if (timelineItem.getTimelineType() == TimelineItem.TYPE_HEADER)     continue;
    selectedPositions.add(pos);
  }
  notifyDataSetChanged();
  actionsListener.onSelectionCountChanged(selectedPositions.size(),mediaItems.size());
}
