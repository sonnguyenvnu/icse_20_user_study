/** 
 * ??
 * @param corpus            ???
 * @param maxIteration      ??????
 * @param averagePerceptron ???????????
 * @return ???????????
 */
public BinaryClassificationFMeasure train(String corpus,int maxIteration,boolean averagePerceptron){
  FeatureMap featureMap=new LockableFeatureMap(new TagSet(TaskType.CLASSIFICATION));
  featureMap.mutable=true;
  Instance[] instanceList=readInstance(corpus,featureMap);
  model=averagePerceptron ? trainAveragedPerceptron(instanceList,featureMap,maxIteration) : trainNaivePerceptron(instanceList,featureMap,maxIteration);
  featureMap.mutable=false;
  return evaluate(instanceList);
}
