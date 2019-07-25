public Integer call(){
  smoothingOnlyMass=0;
  int changed=0;
  for (int doc=startDoc; doc < data.size() && doc < startDoc + numDocs; doc++) {
    Instance instance=data.get(doc).instance;
    alpha=model.alphaCache[doc];
    alphaSum=model.alphaSumCache[doc];
    smoothingOnlyMass=0.0;
    for (int topic=0; topic < numTopics; topic++) {
      smoothingOnlyMass+=alpha[topic] * beta / (tokensPerTopic[topic] + betaSum);
      cachedCoefficients[topic]=alpha[topic] / (tokensPerTopic[topic] + betaSum);
    }
    FeatureSequence tokenSequence=(FeatureSequence)instance.getData();
    LabelSequence topicSequence=(LabelSequence)data.get(doc).topicSequence;
    changed+=sampleTopicsForOneDoc(tokenSequence,topicSequence,true);
  }
  if (shouldBuildLocalCounts) {
    buildLocalTypeTopicCounts();
  }
  shouldSaveState=false;
  return changed;
}
