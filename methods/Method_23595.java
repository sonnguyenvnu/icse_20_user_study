/** 
 * Returns a normalized version of this array. Called getPercent() for consistency with the Dict classes. It's a getter method because it needs to returns a new list (because IntList/Dict can't do percentages or normalization in place on int values).
 */
public DoubleList getPercent(){
  double sum=0;
  for (  double value : array()) {
    sum+=value;
  }
  DoubleList outgoing=new DoubleList(count);
  for (int i=0; i < count; i++) {
    double percent=data[i] / sum;
    outgoing.set(i,percent);
  }
  return outgoing;
}
