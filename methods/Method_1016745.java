public void run(){
  Alphabet alphabet=dictOfSize(20);
  Clustering training=sampleClustering(alphabet);
  Pipe clusterPipe=new OverlappingFeaturePipe();
  System.err.println("Training with " + training);
  InstanceList trainList=new InstanceList(clusterPipe);
  trainList.addThruPipe(new ClusterSampleIterator(training,random,0.5,100));
  System.err.println("Created " + trainList.size() + " instances.");
  Classifier me=new MaxEntTrainer().train(trainList);
  ClassifyingNeighborEvaluator eval=new ClassifyingNeighborEvaluator(me,"YES");
  Trial trial=new Trial(me,trainList);
  System.err.println(new ConfusionMatrix(trial));
  InfoGain ig=new InfoGain(trainList);
  ig.print();
  Clusterer clusterer=new GreedyAgglomerativeByDensity(training.getInstances().getPipe(),eval,0.5,false,new java.util.Random(1));
  Clustering testing=sampleClustering(alphabet);
  InstanceList testList=testing.getInstances();
  Clustering predictedClusters=clusterer.cluster(testList);
  System.err.println("\n\nEvaluating System: " + clusterer);
  ClusteringEvaluators evaluators=new ClusteringEvaluators(new ClusteringEvaluator[]{new BCubedEvaluator(),new PairF1Evaluator(),new MUCEvaluator(),new AccuracyEvaluator()});
  System.err.println("truth:" + testing);
  System.err.println("pred: " + predictedClusters);
  System.err.println(evaluators.evaluate(testing,predictedClusters));
}
