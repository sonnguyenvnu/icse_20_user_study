@Override public int compareTo(BeamElement beamElement){
  float diff=score - beamElement.score;
  if (diff > 0)   return 2;
  if (diff < 0)   return -2;
  if (index != beamElement.index)   return beamElement.index - index;
  return beamElement.action - action;
}
