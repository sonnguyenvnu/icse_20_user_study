/** 
 * Test a maxent classifier. The data representation is the same as for training.
 * @param classifier the classifier to test
 * @param data an iterator over labeled instances
 * @return accuracy on the data
 */
static public double test(Classifier classifier,Iterator<Instance> data){
  InstanceList testList=new InstanceList(classifier.getInstancePipe());
  testList.addThruPipe(data);
  logger.info("# test instances = " + testList.size());
  double accuracy=classifier.getAccuracy(testList);
  return accuracy;
}
