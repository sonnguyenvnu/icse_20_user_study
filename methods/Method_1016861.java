public void estimate(InstanceList documents,InstanceList testing,int numIterations,int showTopicsInterval,int outputModelInterval,int optimizeInterval,String outputModelFilename,Randoms r){
  instances=documents;
  numTypes=instances.getDataAlphabet().size();
  int numDocs=instances.size();
  superTopics=new int[numDocs][];
  subTopics=new int[numDocs][];
  superSubCounts=new int[numSuperTopics + 1][numSubTopics + 1];
  superCounts=new int[numSuperTopics + 1];
  superWeights=new double[numSuperTopics + 1];
  subWeights=new double[numSubTopics];
  superSubWeights=new double[numSuperTopics + 1][numSubTopics + 1];
  cumulativeSuperWeights=new double[numSuperTopics];
  typeTopicCounts=new int[numTypes][1 + numSuperTopics + numSubTopics];
  tokensPerTopic=new int[1 + numSuperTopics + numSubTopics];
  tokensPerSuperTopic=new int[numSuperTopics + 1];
  tokensPerSuperSubTopic=new int[numSuperTopics + 1][numSubTopics + 1];
  betaSum=beta * numTypes;
  long startTime=System.currentTimeMillis();
  int maxTokens=0;
  int superTopic, subTopic, seqLen;
  for (int doc=0; doc < numDocs; doc++) {
    int[] localTokensPerSuperTopic=new int[numSuperTopics + 1];
    int[][] localTokensPerSuperSubTopic=new int[numSuperTopics + 1][numSubTopics + 1];
    FeatureSequence fs=(FeatureSequence)instances.get(doc).getData();
    seqLen=fs.getLength();
    if (seqLen > maxTokens) {
      maxTokens=seqLen;
    }
    numTokens+=seqLen;
    superTopics[doc]=new int[seqLen];
    subTopics[doc]=new int[seqLen];
    for (int position=0; position < seqLen; position++) {
      superTopic=r.nextInt(numSuperTopics);
      subTopic=r.nextInt(numSubTopics);
      int level=r.nextInt(NUM_LEVELS);
      if (level == ROOT_TOPIC) {
        superTopics[doc][position]=numSuperTopics;
        subTopics[doc][position]=numSubTopics;
        typeTopicCounts[fs.getIndexAtPosition(position)][0]++;
        tokensPerTopic[0]++;
        tokensPerSuperTopic[numSuperTopics]++;
        tokensPerSuperSubTopic[numSuperTopics][numSubTopics]++;
        if (localTokensPerSuperTopic[numSuperTopics] == 0) {
          superTopicDocumentFrequencies[numSuperTopics]++;
          sumDocumentFrequencies++;
        }
        localTokensPerSuperTopic[numSuperTopics]++;
      }
 else       if (level == SUPER_TOPIC) {
        superTopics[doc][position]=superTopic;
        subTopics[doc][position]=numSubTopics;
        typeTopicCounts[fs.getIndexAtPosition(position)][1 + superTopic]++;
        tokensPerTopic[1 + superTopic]++;
        tokensPerSuperTopic[superTopic]++;
        tokensPerSuperSubTopic[superTopic][numSubTopics]++;
        if (localTokensPerSuperTopic[superTopic] == 0) {
          superTopicDocumentFrequencies[superTopic]++;
          sumDocumentFrequencies++;
        }
        localTokensPerSuperTopic[superTopic]++;
        if (localTokensPerSuperSubTopic[superTopic][numSubTopics] == 0) {
          superSubTopicDocumentFrequencies[superTopic][numSubTopics]++;
          sumSuperTopicDocumentFrequencies[superTopic]++;
        }
        localTokensPerSuperSubTopic[superTopic][numSubTopics]++;
      }
 else {
        superTopics[doc][position]=superTopic;
        subTopics[doc][position]=subTopic;
        typeTopicCounts[fs.getIndexAtPosition(position)][1 + numSuperTopics + subTopic]++;
        tokensPerTopic[1 + numSuperTopics + subTopic]++;
        tokensPerSuperTopic[superTopic]++;
        tokensPerSuperSubTopic[superTopic][subTopic]++;
        if (localTokensPerSuperTopic[superTopic] == 0) {
          superTopicDocumentFrequencies[superTopic]++;
          sumDocumentFrequencies++;
        }
        localTokensPerSuperTopic[superTopic]++;
        if (localTokensPerSuperSubTopic[superTopic][subTopic] == 0) {
          superSubTopicDocumentFrequencies[superTopic][subTopic]++;
          sumSuperTopicDocumentFrequencies[superTopic]++;
        }
        localTokensPerSuperSubTopic[superTopic][subTopic]++;
      }
    }
  }
  superTopicPriorWeights=new double[numSuperTopics + 1];
  superSubTopicPriorWeights=new double[numSuperTopics][numSubTopics + 1];
  cacheSuperTopicPrior();
  for (superTopic=0; superTopic < numSuperTopics; superTopic++) {
    cacheSuperSubTopicPrior(superTopic);
  }
  for (int iterations=1; iterations < numIterations; iterations++) {
    long iterationStart=System.currentTimeMillis();
    for (int doc=0; doc < superTopics.length; doc++) {
      sampleTopicsForOneDoc((FeatureSequence)instances.get(doc).getData(),superTopics[doc],subTopics[doc],r);
    }
    if (showTopicsInterval != 0 && iterations % showTopicsInterval == 0) {
      logger.info(printTopWords(8,false));
    }
    logger.fine((System.currentTimeMillis() - iterationStart) + " ");
    if (iterations % 10 == 0) {
      logger.info("<" + iterations + "> LL: " + formatter.format(modelLogLikelihood() / numTokens));
    }
  }
}
