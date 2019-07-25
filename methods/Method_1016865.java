public String call() throws Exception {
  int numCallables=callables.length;
  for (int type=0; type < numTypes; type++) {
    if (type % numCallables != threadID) {
      continue;
    }
    int[] targetCounts=typeTopicCounts[type];
    for (int thread=0; thread < numCallables; thread++) {
      int[] sourceCounts=callables[thread].getTypeTopicCounts()[type];
      int sourceIndex=0;
      while (sourceIndex < sourceCounts.length && sourceCounts[sourceIndex] > 0) {
        int topic=sourceCounts[sourceIndex] & topicMask;
        int count=sourceCounts[sourceIndex] >> topicBits;
        int targetIndex=0;
        int currentTopic=targetCounts[targetIndex] & topicMask;
        int currentCount;
        while (targetCounts[targetIndex] > 0 && currentTopic != topic) {
          targetIndex++;
          if (targetIndex == targetCounts.length) {
            System.out.println("overflow in merging on type " + type + " for topic " + topic);
            StringBuilder out=new StringBuilder();
            for (            int value : targetCounts) {
              out.append(value + " ");
            }
            System.out.println(out.toString());
          }
          currentTopic=targetCounts[targetIndex] & topicMask;
        }
        currentCount=targetCounts[targetIndex] >> topicBits;
        targetCounts[targetIndex]=((currentCount + count) << topicBits) + topic;
        while (targetIndex > 0 && targetCounts[targetIndex] > targetCounts[targetIndex - 1]) {
          int temp=targetCounts[targetIndex];
          targetCounts[targetIndex]=targetCounts[targetIndex - 1];
          targetCounts[targetIndex - 1]=temp;
          targetIndex--;
        }
        sourceIndex++;
      }
    }
  }
  return "Done";
}
