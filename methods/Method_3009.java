public Configuration parse(Sentence sentence,boolean rootFirst,int beamWidth,int numOfThreads) throws ExecutionException, InterruptedException {
  Configuration initialConfiguration=new Configuration(sentence,rootFirst);
  ArrayList<Configuration> beam=new ArrayList<Configuration>(beamWidth);
  beam.add(initialConfiguration);
  while (!ArcEager.isTerminal(beam)) {
    TreeSet<BeamElement> beamPreserver=new TreeSet<BeamElement>();
    if (numOfThreads == 1) {
      sortBeam(beam,beamPreserver,false,new Instance(sentence,new HashMap<Integer,Edge>()),beamWidth,rootFirst,featureLength,classifier,dependencyRelations);
    }
 else {
      for (int b=0; b < beam.size(); b++) {
        pool.submit(new BeamScorerThread(true,classifier,beam.get(b),dependencyRelations,featureLength,b,rootFirst));
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
