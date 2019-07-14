/** 
 * ?????????????
 * @return
 */
public TreeSet<TermFrequency> values(){
  TreeSet<TermFrequency> set=new TreeSet<TermFrequency>(Collections.reverseOrder());
  for (  Map.Entry<String,TermFrequency> entry : entrySet()) {
    set.add(entry.getValue());
  }
  return set;
}
