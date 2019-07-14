/** 
 * Sum all of the values in this dictionary, then return a new FloatDict of each key, divided by the total sum. The total for all values will be ~1.0.
 * @return a FloatDict with the original keys, mapped to their pct of the total
 */
public DoubleDict getPercent(){
  double sum=sum();
  DoubleDict outgoing=new DoubleDict();
  for (int i=0; i < size(); i++) {
    double percent=value(i) / sum;
    outgoing.set(key(i),percent);
  }
  return outgoing;
}
