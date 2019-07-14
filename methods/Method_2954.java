public static double[] evaluate(String testPath,String predictedPath,HashSet<String> puncTags) throws IOException {
  CoNLLReader goldReader=new CoNLLReader(testPath);
  CoNLLReader predictedReader=new CoNLLReader(predictedPath);
  ArrayList<CompactTree> goldConfiguration=goldReader.readStringData();
  ArrayList<CompactTree> predConfiguration=predictedReader.readStringData();
  float unlabMatch=0f;
  float labMatch=0f;
  int all=0;
  float fullULabMatch=0f;
  float fullLabMatch=0f;
  int numTree=0;
  for (int i=0; i < predConfiguration.size(); i++) {
    HashMap<Integer,Pair<Integer,String>> goldDeps=goldConfiguration.get(i).goldDependencies;
    HashMap<Integer,Pair<Integer,String>> predDeps=predConfiguration.get(i).goldDependencies;
    ArrayList<String> goldTags=goldConfiguration.get(i).posTags;
    numTree++;
    boolean fullMatch=true;
    boolean fullUnlabMatch=true;
    for (    int dep : goldDeps.keySet()) {
      if (!puncTags.contains(goldTags.get(dep - 1).trim())) {
        all++;
        int gh=goldDeps.get(dep).first;
        int ph=predDeps.get(dep).first;
        String gl=goldDeps.get(dep).second.trim();
        String pl=predDeps.get(dep).second.trim();
        if (ph == gh) {
          unlabMatch++;
          if (pl.equals(gl))           labMatch++;
 else {
            fullMatch=false;
          }
        }
 else {
          fullMatch=false;
          fullUnlabMatch=false;
        }
      }
    }
    if (fullMatch)     fullLabMatch++;
    if (fullUnlabMatch)     fullULabMatch++;
  }
  double labeledAccuracy=100.0 * labMatch / all;
  double unlabaledAccuracy=100.0 * unlabMatch / all;
  double labExact=100.0 * fullLabMatch / numTree;
  double ulabExact=100.0 * fullULabMatch / numTree;
  return new double[]{unlabaledAccuracy,labeledAccuracy};
}
