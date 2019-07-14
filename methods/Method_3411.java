/** 
 * ??
 * @param instanceList
 * @return
 */
public BinaryClassificationFMeasure evaluate(Instance[] instanceList){
  int TP=0, FP=0, FN=0;
  for (  Instance instance : instanceList) {
    int y=model.decode(instance.x);
    if (y == 1) {
      if (instance.y == 1)       ++TP;
 else       ++FP;
    }
 else     if (instance.y == 1)     ++FN;
  }
  float p=TP / (float)(TP + FP) * 100;
  float r=TP / (float)(TP + FN) * 100;
  return new BinaryClassificationFMeasure(p,r,2 * p * r / (p + r));
}
