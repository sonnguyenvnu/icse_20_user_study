/** 
 * ????Viterbi????????????
 * @param roleTagList               ????
 * @param transformMatrixDictionary ????
 * @param < E >                       EnumItem?????
 * @return ????
 */
public static <E extends Enum<E>>List<E> computeEnum(List<EnumItem<E>> roleTagList,TransformMatrixDictionary<E> transformMatrixDictionary){
  int length=roleTagList.size() - 1;
  List<E> tagList=new ArrayList<E>(roleTagList.size());
  double[][] cost=new double[2][];
  Iterator<EnumItem<E>> iterator=roleTagList.iterator();
  EnumItem<E> start=iterator.next();
  E pre=start.labelMap.entrySet().iterator().next().getKey();
  tagList.add(pre);
  Set<E> preTagSet;
{
    EnumItem<E> item=iterator.next();
    cost[0]=new double[item.labelMap.size()];
    int j=0;
    for (    E cur : item.labelMap.keySet()) {
      cost[0][j]=transformMatrixDictionary.transititon_probability[pre.ordinal()][cur.ordinal()] - Math.log((item.getFrequency(cur) + 1e-8) / transformMatrixDictionary.getTotalFrequency(cur));
      ++j;
    }
    preTagSet=item.labelMap.keySet();
  }
  for (int i=1; i < length; ++i) {
    int index_i=i & 1;
    int index_i_1=1 - index_i;
    EnumItem<E> item=iterator.next();
    cost[index_i]=new double[item.labelMap.size()];
    double perfect_cost_line=Double.MAX_VALUE;
    int k=0;
    Set<E> curTagSet=item.labelMap.keySet();
    for (    E cur : curTagSet) {
      cost[index_i][k]=Double.MAX_VALUE;
      int j=0;
      for (      E p : preTagSet) {
        double now=cost[index_i_1][j] + transformMatrixDictionary.transititon_probability[p.ordinal()][cur.ordinal()] - Math.log((item.getFrequency(cur) + 1e-8) / transformMatrixDictionary.getTotalFrequency(cur));
        if (now < cost[index_i][k]) {
          cost[index_i][k]=now;
          if (now < perfect_cost_line) {
            perfect_cost_line=now;
            pre=p;
          }
        }
        ++j;
      }
      ++k;
    }
    tagList.add(pre);
    preTagSet=curTagSet;
  }
  tagList.add(tagList.get(0));
  return tagList;
}
