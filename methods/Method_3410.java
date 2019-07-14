/** 
 * ??
 * @param corpus
 * @return
 */
public BinaryClassificationFMeasure evaluate(String corpus){
  Instance[] instanceList=readInstance(corpus,model.featureMap);
  return evaluate(instanceList);
}
