public void estimate(InstanceList documents,int numIterations,int optimizeInterval,int showTopicsInterval,int outputModelInterval,String outputModelFilename,Randoms r){
  ilist=documents;
  numTypes=ilist.getDataAlphabet().size();
  int numDocs=ilist.size();
  superTopics=new int[numDocs][];
  subTopics=new int[numDocs][];
  superSubCounts=new int[numSuperTopics][numSubTopics];
  superCounts=new int[numSuperTopics];
  superWeights=new double[numSuperTopics];
  subWeights=new double[numSubTopics];
  superSubWeights=new double[numSuperTopics][numSubTopics];
  cumulativeSuperWeights=new double[numSuperTopics];
  typeSubTopicCounts=new int[numTypes][numSubTopics];
  tokensPerSubTopic=new int[numSubTopics];
  tokensPerSuperTopic=new int[numSuperTopics];
  tokensPerSuperSubTopic=new int[numSuperTopics][numSubTopics];
  vBeta=beta * numTypes;
  long startTime=System.currentTimeMillis();
  int maxTokens=0;
  int superTopic, subTopic, seqLen;
  for (int di=0; di < numDocs; di++) {
    FeatureSequence fs=(FeatureSequence)ilist.get(di).getData();
    seqLen=fs.getLength();
    if (seqLen > maxTokens) {
      maxTokens=seqLen;
    }
    numTokens+=seqLen;
    superTopics[di]=new int[seqLen];
    subTopics[di]=new int[seqLen];
    for (int si=0; si < seqLen; si++) {
      superTopic=r.nextInt(numSuperTopics);
      superTopics[di][si]=superTopic;
      tokensPerSuperTopic[superTopic]++;
      subTopic=r.nextInt(numSubTopics);
      subTopics[di][si]=subTopic;
      typeSubTopicCounts[fs.getIndexAtPosition(si)][subTopic]++;
      tokensPerSubTopic[subTopic]++;
      tokensPerSuperSubTopic[superTopic][subTopic]++;
    }
  }
  System.out.println("max tokens: " + maxTokens);
  superTopicHistograms=new int[numSuperTopics][maxTokens + 1];
  subTopicHistograms=new int[numSuperTopics][numSubTopics][maxTokens + 1];
  for (int iterations=0; iterations < numIterations; iterations++) {
    long iterationStart=System.currentTimeMillis();
    clearHistograms();
    sampleTopicsForAllDocs(r);
    if (iterations > 0) {
      if (showTopicsInterval != 0 && iterations % showTopicsInterval == 0) {
        System.out.println();
        printTopWords(5,false);
      }
      if (outputModelInterval != 0 && iterations % outputModelInterval == 0) {
      }
      if (optimizeInterval != 0 && iterations % optimizeInterval == 0) {
        long optimizeTime=System.currentTimeMillis();
        for (superTopic=0; superTopic < numSuperTopics; superTopic++) {
          learnParameters(subAlphas[superTopic],subTopicHistograms[superTopic],superTopicHistograms[superTopic]);
          subAlphaSums[superTopic]=0.0;
          for (subTopic=0; subTopic < numSubTopics; subTopic++) {
            subAlphaSums[superTopic]+=subAlphas[superTopic][subTopic];
          }
        }
        System.out.print("[o:" + (System.currentTimeMillis() - optimizeTime) + "]");
      }
    }
    if (iterations > 1107) {
      printWordCounts();
    }
    if (iterations % 10 == 0)     System.out.println("<" + iterations + "> ");
    System.out.print((System.currentTimeMillis() - iterationStart) + " ");
    System.out.flush();
  }
  long seconds=Math.round((System.currentTimeMillis() - startTime) / 1000.0);
  long minutes=seconds / 60;
  seconds%=60;
  long hours=minutes / 60;
  minutes%=60;
  long days=hours / 24;
  hours%=24;
  System.out.print("\nTotal time: ");
  if (days != 0) {
    System.out.print(days);
    System.out.print(" days ");
  }
  if (hours != 0) {
    System.out.print(hours);
    System.out.print(" hours ");
  }
  if (minutes != 0) {
    System.out.print(minutes);
    System.out.print(" minutes ");
  }
  System.out.print(seconds);
  System.out.println(" seconds");
}
