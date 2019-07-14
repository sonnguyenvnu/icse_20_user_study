/** 
 * Get the last consecutive string position
 * @param j      current value position
 * @param value  value content
 * @param values values
 * @return the last consecutive string position
 */
private int getLastRangNum(int j,String value,List<String> values){
  if (value == null) {
    return -1;
  }
  if (j > 0) {
    String preValue=values.get(j - 1);
    if (value.equals(preValue)) {
      return -1;
    }
  }
  int last=j;
  for (int i=last + 1; i < values.size(); i++) {
    String current=values.get(i);
    if (value.equals(current)) {
      last=i;
    }
 else {
      if (i > j) {
        break;
      }
    }
  }
  return last;
}
