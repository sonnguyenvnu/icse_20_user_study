public void setGridLayoutManager(GridLayoutManager gridLayoutManager){
  gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
    @Override public int getSpanSize(    int position){
      TimelineItem timelineItem=getItem(position);
      if (timelineItem.getTimelineType() == TimelineItem.TYPE_HEADER)       return timelineGridSize;
      return 1;
    }
  }
);
}
