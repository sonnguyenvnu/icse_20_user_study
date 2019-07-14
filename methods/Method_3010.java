private ArrayList<Configuration> commitActionInBeam(int beamWidth,ArrayList<Configuration> beam,TreeSet<BeamElement> beamPreserver){
  ArrayList<Configuration> repBeam=new ArrayList<Configuration>(beamWidth);
  for (  BeamElement beamElement : beamPreserver.descendingSet()) {
    if (repBeam.size() >= beamWidth)     break;
    int b=beamElement.index;
    int action=beamElement.action;
    int label=beamElement.label;
    float score=beamElement.score;
    Configuration newConfig=beam.get(b).clone();
    ArcEager.commitAction(action,label,score,dependencyRelations,newConfig);
    repBeam.add(newConfig);
  }
  beam=repBeam;
  return beam;
}
