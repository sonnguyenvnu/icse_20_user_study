public void estimate(int iterationsThisRound) throws IOException {
  long startTime=System.currentTimeMillis();
  int maxIteration=iterationsSoFar + iterationsThisRound;
  for (; iterationsSoFar <= maxIteration; iterationsSoFar++) {
    long iterationStart=System.currentTimeMillis();
    if (showTopicsInterval != 0 && iterationsSoFar != 0 && iterationsSoFar % showTopicsInterval == 0) {
      System.out.println();
      printTopWords(System.out,wordsPerTopic,false);
      if (testing != null) {
        double el=empiricalLikelihood(1000,testing);
        double ll=modelLogLikelihood();
        double mi=topicLabelMutualInformation();
        System.out.println(ll + "\t" + el + "\t" + mi);
      }
    }
    if (saveStateInterval != 0 && iterationsSoFar % saveStateInterval == 0) {
      this.printState(new File(stateFilename + '.' + iterationsSoFar));
    }
    if (iterationsSoFar > burninPeriod && optimizeInterval != 0 && iterationsSoFar % optimizeInterval == 0) {
      alphaSum=Dirichlet.learnParameters(alpha,topicDocCounts,docLengthCounts);
      smoothingOnlyMass=0.0;
      for (int topic=0; topic < numTopics; topic++) {
        smoothingOnlyMass+=alpha[topic] * beta / (tokensPerTopic[topic] + betaSum);
        cachedCoefficients[topic]=alpha[topic] / (tokensPerTopic[topic] + betaSum);
      }
      clearHistograms();
    }
    topicTermCount=betaTopicCount=smoothingOnlyCount=0;
    int numDocs=data.size();
    for (int di=0; di < numDocs; di++) {
      FeatureSequence tokenSequence=(FeatureSequence)data.get(di).instance.getData();
      LabelSequence topicSequence=(LabelSequence)data.get(di).topicSequence;
      sampleTopicsForOneDoc(tokenSequence,topicSequence,iterationsSoFar >= burninPeriod && iterationsSoFar % saveSampleInterval == 0,true);
    }
    long elapsedMillis=System.currentTimeMillis() - iterationStart;
    if (elapsedMillis < 1000) {
      System.out.print(elapsedMillis + "ms ");
    }
 else {
      System.out.print((elapsedMillis / 1000) + "s ");
    }
    if (iterationsSoFar % 10 == 0) {
      System.out.println("<" + iterationsSoFar + "> ");
      if (printLogLikelihood)       System.out.println(modelLogLikelihood());
    }
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
