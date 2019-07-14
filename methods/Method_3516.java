/** 
 * ????????
 * @param sSentence ??????
 * @param nKind     ??????
 * @param wordNetOptimum
 * @param wordNetAll
 * @return ???????
 */
public List<List<Vertex>> biSegment(char[] sSentence,int nKind,WordNet wordNetOptimum,WordNet wordNetAll){
  List<List<Vertex>> coarseResult=new LinkedList<List<Vertex>>();
  generateWordNet(wordNetAll);
  Graph graph=generateBiGraph(wordNetAll);
  if (HanLP.Config.DEBUG) {
    System.out.printf("?????%s\n",graph.printByTo());
  }
  NShortPath nShortPath=new NShortPath(graph,nKind);
  List<int[]> spResult=nShortPath.getNPaths(nKind * 2);
  if (spResult.size() == 0) {
    throw new RuntimeException(nKind + "-????????????????????????????");
  }
  for (  int[] path : spResult) {
    List<Vertex> vertexes=graph.parsePath(path);
    generateWord(vertexes,wordNetOptimum);
    coarseResult.add(vertexes);
  }
  return coarseResult;
}
