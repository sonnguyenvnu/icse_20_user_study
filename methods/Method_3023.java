private void addToBeam(TreeSet<BeamElement> beamPreserver,int b,float addedScore,int action,int label,int beamWidth){
  beamPreserver.add(new BeamElement(addedScore,b,action,label));
  if (beamPreserver.size() > beamWidth)   beamPreserver.pollFirst();
}
