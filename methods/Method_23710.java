/** 
 * Returns a normalized version of this array. Called getPercent() for consistency with the Dict classes. It's a getter method because it needs to returns a new list (because IntList/Dict can't do percentages or normalization in place on int values).
 */
public FloatList getPercent(){
  double sum=0;
  for (  float value : array()) {
    sum+=value;
  }
  FloatList outgoing=new FloatList(count);
  for (int i=0; i < count; i++) {
    double percent=data[i] / sum;
    outgoing.set(i,(float)percent);
  }
  return outgoing;
}
