public Configuration parsePartial() throws Exception {
  Configuration initialConfiguration=new Configuration(sentence,rootFirst);
  boolean isNonProjective=false;
  if (instance.isNonprojective()) {
    isNonProjective=true;
  }
  ArrayList<Configuration> beam=new ArrayList<Configuration>(beamWidth);
  beam.add(initialConfiguration);
  while (!ArcEager.isTerminal(beam)) {
    TreeSet<BeamElement> beamPreserver=new TreeSet<BeamElement>();
    sortBeam(beam,beamPreserver,isNonProjective,instance,beamWidth,rootFirst,featureLength,classifier,dependencyRelations);
    ArrayList<Configuration> repBeam=new ArrayList<Configuration>(beamWidth);
    for (    BeamElement beamElement : beamPreserver.descendingSet()) {
      if (repBeam.size() >= beamWidth)       break;
      int b=beamElement.index;
      int action=beamElement.action;
      int label=beamElement.label;
      float score=beamElement.score;
      Configuration newConfig=beam.get(b).clone();
      if (action == 0) {
        ArcEager.shift(newConfig.state);
        newConfig.addAction(0);
      }
 else       if (action == 1) {
        ArcEager.reduce(newConfig.state);
        newConfig.addAction(1);
      }
 else       if (action == 2) {
        ArcEager.rightArc(newConfig.state,label);
        newConfig.addAction(3 + label);
      }
 else       if (action == 3) {
        ArcEager.leftArc(newConfig.state,label);
        newConfig.addAction(3 + dependencyRelations.size() + label);
      }
 else       if (action == 4) {
        ArcEager.unShift(newConfig.state);
        newConfig.addAction(2);
      }
      newConfig.setScore(score);
      repBeam.add(newConfig);
    }
    beam=repBeam;
  }
  Configuration bestConfiguration=null;
  float bestScore=Float.NEGATIVE_INFINITY;
  for (  Configuration configuration : beam) {
    if (configuration.getScore(true) > bestScore) {
      bestScore=configuration.getScore(true);
      bestConfiguration=configuration;
    }
  }
  return bestConfiguration;
}
