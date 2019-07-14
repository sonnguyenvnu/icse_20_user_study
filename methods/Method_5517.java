private List<Cue> getDisplayCues(){
  @Cue.AnchorType int positionAnchor=Cue.ANCHOR_TYPE_END;
  int cueBuilderCount=cueBuilders.size();
  List<Cue> cueBuilderCues=new ArrayList<>(cueBuilderCount);
  for (int i=0; i < cueBuilderCount; i++) {
    Cue cue=cueBuilders.get(i).build(Cue.TYPE_UNSET);
    cueBuilderCues.add(cue);
    if (cue != null) {
      positionAnchor=Math.min(positionAnchor,cue.positionAnchor);
    }
  }
  List<Cue> displayCues=new ArrayList<>(cueBuilderCount);
  for (int i=0; i < cueBuilderCount; i++) {
    Cue cue=cueBuilderCues.get(i);
    if (cue != null) {
      if (cue.positionAnchor != positionAnchor) {
        cue=cueBuilders.get(i).build(positionAnchor);
      }
      displayCues.add(cue);
    }
  }
  return displayCues;
}
