private void updateViews(){
  for (int i=0; i < trackViews.length; i++) {
    for (int j=0; j < trackViews[i].length; j++) {
      trackViews[i][j].setChecked(override != null && override.groupIndex == i && override.containsTrack(j));
    }
  }
}
