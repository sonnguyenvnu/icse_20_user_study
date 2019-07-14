private int getExtractedSamplesCount(){
  int extractedSamplesCount=0;
  for (  SampleQueue sampleQueue : sampleQueues) {
    extractedSamplesCount+=sampleQueue.getWriteIndex();
  }
  return extractedSamplesCount;
}
