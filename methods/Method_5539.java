private List<Cue> getDisplayCues(){
  List<Cea708Cue> displayCues=new ArrayList<>();
  for (int i=0; i < NUM_WINDOWS; i++) {
    if (!cueBuilders[i].isEmpty() && cueBuilders[i].isVisible()) {
      displayCues.add(cueBuilders[i].build());
    }
  }
  Collections.sort(displayCues);
  return Collections.unmodifiableList(displayCues);
}
