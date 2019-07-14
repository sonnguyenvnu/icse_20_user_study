/** 
 * ????????????map???
 * @param other
 */
public void combine(SimpleItem other){
  for (  Map.Entry<String,Integer> entry : other.labelMap.entrySet()) {
    addLabel(entry.getKey(),entry.getValue());
  }
}
