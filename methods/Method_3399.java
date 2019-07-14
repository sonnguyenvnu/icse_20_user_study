protected double[] evaluate(String developFile,final LinearModel model) throws IOException {
  final int[] stat=new int[2];
  IOUtility.loadInstance(developFile,new InstanceHandler(){
    @Override public boolean process(    Sentence sentence){
      Utility.normalize(sentence);
      Instance instance=createInstance(sentence,model.featureMap);
      IOUtility.evaluate(instance,model,stat);
      return false;
    }
  }
);
  return new double[]{stat[1] / (double)stat[0] * 100};
}
