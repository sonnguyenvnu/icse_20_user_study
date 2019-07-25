public void train(InstanceList instances,int numThreads,int numSamples){
  ExecutorService executor=Executors.newFixedThreadPool(numThreads);
  WordEmbeddingCallable[] callables=new WordEmbeddingCallable[numThreads];
  for (int thread=0; thread < numThreads; thread++) {
    callables[thread]=new WordEmbeddingCallable(this,instances,numSamples,numThreads,thread);
    callables[thread].setOrdering(orderingStrategy);
  }
  long startTime=System.currentTimeMillis();
  double difference=0.0;
  for (int iteration=0; iteration < numIterations; iteration++) {
    long wordsSoFar=0;
    try {
      List<Future<Long>> futures=executor.invokeAll(Arrays.asList(callables));
      for (      Future<Long> future : futures) {
        wordsSoFar+=future.get();
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
    long runningMillis=System.currentTimeMillis() - startTime;
    System.out.format("%d\t%d\t%fk w/s %.3f avg\n",wordsSoFar,runningMillis,(double)wordsSoFar / runningMillis,averageAbsWeight());
    if (queryWord != null && vocabulary.contains(queryWord)) {
      findClosest(copy(queryWord));
    }
  }
  executor.shutdownNow();
}
