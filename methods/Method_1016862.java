public void estimate(InstanceList documents,int numIterations,int showTopicsInterval,int outputModelInterval,String outputModelFilename,Randoms r){
  ilist=documents.shallowClone();
  numTypes=ilist.getDataAlphabet().size();
  int numDocs=ilist.size();
  topics=new int[numDocs][];
  docTopicCounts=new int[numDocs][numTopics];
  typeTopicCounts=new int[numTypes][numTopics];
  tokensPerTopic=new int[numTopics];
  tAlpha=alpha * numTopics;
  vBeta=beta * numTypes;
  long startTime=System.currentTimeMillis();
  int topic, seqLen;
  FeatureSequence fs;
  for (int di=0; di < numDocs; di++) {
    try {
      fs=(FeatureSequence)ilist.get(di).getData();
    }
 catch (    ClassCastException e) {
      System.err.println("LDA and other topic models expect FeatureSequence data, not FeatureVector data.  " + "With text2vectors, you can obtain such data with --keep-sequence or --keep-bisequence.");
      throw e;
    }
    seqLen=fs.getLength();
    numTokens+=seqLen;
    topics[di]=new int[seqLen];
    for (int si=0; si < seqLen; si++) {
      topic=r.nextInt(numTopics);
      topics[di][si]=topic;
      docTopicCounts[di][topic]++;
      typeTopicCounts[fs.getIndexAtPosition(si)][topic]++;
      tokensPerTopic[topic]++;
    }
  }
  this.estimate(0,numDocs,numIterations,showTopicsInterval,outputModelInterval,outputModelFilename,r);
}
