public String call() throws Exception {
  int numTypes=typeTopicCounts.length;
  int numTopics=tokensPerTopic.length;
  int[] callableTotals=targetCallable.getTokensPerTopic();
  System.arraycopy(tokensPerTopic,0,callableTotals,0,numTopics);
  int[][] callableCounts=targetCallable.getTypeTopicCounts();
  for (int type=0; type < numTypes; type++) {
    int[] targetCounts=callableCounts[type];
    int[] sourceCounts=typeTopicCounts[type];
    int index=0;
    while (index < sourceCounts.length) {
      if (sourceCounts[index] != 0) {
        targetCounts[index]=sourceCounts[index];
      }
 else       if (targetCounts[index] != 0) {
        targetCounts[index]=0;
      }
 else {
        break;
      }
      index++;
    }
  }
  return "Done";
}
