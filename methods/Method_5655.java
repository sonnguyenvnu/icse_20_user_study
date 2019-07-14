@Override public List<Cue> getCues(long timeUs){
  ArrayList<Cue> list=null;
  WebvttCue firstNormalCue=null;
  SpannableStringBuilder normalCueTextBuilder=null;
  for (int i=0; i < numCues; i++) {
    if ((cueTimesUs[i * 2] <= timeUs) && (timeUs < cueTimesUs[i * 2 + 1])) {
      if (list == null) {
        list=new ArrayList<>();
      }
      WebvttCue cue=cues.get(i);
      if (cue.isNormalCue()) {
        if (firstNormalCue == null) {
          firstNormalCue=cue;
        }
 else         if (normalCueTextBuilder == null) {
          normalCueTextBuilder=new SpannableStringBuilder();
          normalCueTextBuilder.append(firstNormalCue.text).append("\n").append(cue.text);
        }
 else {
          normalCueTextBuilder.append("\n").append(cue.text);
        }
      }
 else {
        list.add(cue);
      }
    }
  }
  if (normalCueTextBuilder != null) {
    list.add(new WebvttCue(normalCueTextBuilder));
  }
 else   if (firstNormalCue != null) {
    list.add(firstNormalCue);
  }
  if (list != null) {
    return list;
  }
 else {
    return Collections.emptyList();
  }
}
