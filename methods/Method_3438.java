public static void evaluate(Instance instance,LinearModel model,int[] stat){
  int[] predLabel=new int[instance.length()];
  model.viterbiDecode(instance,predLabel);
  stat[0]+=instance.tagArray.length;
  for (int i=0; i < predLabel.length; i++) {
    if (predLabel[i] == instance.tagArray[i]) {
      ++stat[1];
    }
  }
}
