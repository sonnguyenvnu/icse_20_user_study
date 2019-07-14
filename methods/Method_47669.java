private void updateMaxMinLengths(){
  maxLength=0;
  minLength=Long.MAX_VALUE;
  shouldShowLabels=true;
  for (  Streak s : streaks) {
    maxLength=Math.max(maxLength,s.getLength());
    minLength=Math.min(minLength,s.getLength());
    float lw1=paint.measureText(dateFormat.format(s.getStart().toJavaDate()));
    float lw2=paint.measureText(dateFormat.format(s.getEnd().toJavaDate()));
    maxLabelWidth=Math.max(maxLabelWidth,Math.max(lw1,lw2));
  }
  if (width - 2 * maxLabelWidth < width * 0.25f) {
    maxLabelWidth=0;
    shouldShowLabels=false;
  }
}
