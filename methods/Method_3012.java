public Configuration parsePartial(Instance instance,Sentence sentence,boolean rootFirst,int beamWidth,int numOfThreads) throws ExecutionException, InterruptedException {
  Configuration initialConfiguration=new Configuration(sentence,rootFirst);
  boolean isNonProjective=false;
  if (instance.isNonprojective()) {
    isNonProjective=true;
  }
  ArrayList<Configuration> beam=new ArrayList<Configuration>(beamWidth);
  beam.add(initialConfiguration);
  while (!ArcEager.isTerminal(beam)) {
    TreeSet<BeamElement> beamPreserver=new TreeSet<BeamElement>();
    if (numOfThreads == 1) {
      parsePartialWithOneThread(beam,beamPreserver,isNonProjective,instance,beamWidth,rootFirst);
    }
 else {
      for (int b=0; b < beam.size(); b++) {
        pool.submit(new PartialTreeBeamScorerThread(true,classifier,instance,beam.get(b),dependencyRelations,featureLength,b));
      }
      fetchBeamFromPool(beamWidth,beam,beamPreserver);
    }
    beam=commitActionInBeam(beamWidth,beam,beamPreserver);
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
