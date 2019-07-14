/** 
 * Sum all of the values in this dictionary, then return a new FloatDict of each key, divided by the total sum. The total for all values will be ~1.0.
 * @return an IntDict with the original keys, mapped to their pct of the total
 */
public FloatDict getPercent(){
  double sum=sum();
  FloatDict outgoing=new FloatDict();
  for (int i=0; i < size(); i++) {
    double percent=value(i) / sum;
    outgoing.set(key(i),(float)percent);
  }
  return outgoing;
}
