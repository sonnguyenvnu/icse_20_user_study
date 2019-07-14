@Override public List<Term> segSentence(char[] sentence){
  WordNet wordNetOptimum=new WordNet(sentence);
  WordNet wordNetAll=new WordNet(wordNetOptimum.charArray);
  generateWordNet(wordNetAll);
  Graph graph=generateBiGraph(wordNetAll);
  if (HanLP.Config.DEBUG) {
    System.out.printf("?????%s\n",graph.printByTo());
  }
  List<Vertex> vertexList=dijkstra(graph);
  if (config.useCustomDictionary) {
    if (config.indexMode > 0)     combineByCustomDictionary(vertexList,wordNetAll);
 else     combineByCustomDictionary(vertexList);
  }
  if (HanLP.Config.DEBUG) {
    System.out.println("????" + convert(vertexList,false));
  }
  if (config.numberQuantifierRecognize) {
    mergeNumberQuantifier(vertexList,wordNetAll,config);
  }
  if (config.ner) {
    wordNetOptimum.addAll(vertexList);
    int preSize=wordNetOptimum.size();
    if (config.nameRecognize) {
      PersonRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (config.translatedNameRecognize) {
      TranslatedPersonRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (config.japaneseNameRecognize) {
      JapanesePersonRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (config.placeRecognize) {
      PlaceRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (config.organizationRecognize) {
      graph=generateBiGraph(wordNetOptimum);
      vertexList=dijkstra(graph);
      wordNetOptimum.clear();
      wordNetOptimum.addAll(vertexList);
      preSize=wordNetOptimum.size();
      OrganizationRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (wordNetOptimum.size() != preSize) {
      graph=generateBiGraph(wordNetOptimum);
      vertexList=dijkstra(graph);
      if (HanLP.Config.DEBUG) {
        System.out.printf("?????\n%s\n",wordNetOptimum);
        System.out.printf("?????%s\n",graph.printByTo());
      }
    }
  }
  if (config.indexMode > 0) {
    return decorateResultForIndexMode(vertexList,wordNetAll);
  }
  if (config.speechTagging) {
    speechTagging(vertexList);
  }
  return convert(vertexList,config.offset);
}
