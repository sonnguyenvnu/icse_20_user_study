/** 
 * ??????HMM??
 * @param vertexList                ??Vertex.B?????
 * @param transformMatrixDictionary ?????????
 */
public static void compute(List<Vertex> vertexList,TransformMatrix transformMatrixDictionary){
  if (Nature.values().length != transformMatrixDictionary.states.length)   transformMatrixDictionary.extend(Nature.values().length);
  int length=vertexList.size() - 1;
  double[][] cost=new double[2][];
  Iterator<Vertex> iterator=vertexList.iterator();
  Vertex start=iterator.next();
  Nature pre=start.attribute.nature[0];
  Vertex preItem;
  Nature[] preTagSet;
{
    Vertex item=iterator.next();
    cost[0]=new double[item.attribute.nature.length];
    int j=0;
    int curIndex=0;
    for (    Nature cur : item.attribute.nature) {
      cost[0][j]=transformMatrixDictionary.transititon_probability[pre.ordinal()][cur.ordinal()] - Math.log((item.attribute.frequency[curIndex] + 1e-8) / transformMatrixDictionary.getTotalFrequency(cur.ordinal()));
      ++j;
      ++curIndex;
    }
    preTagSet=item.attribute.nature;
    preItem=item;
  }
  for (int i=1; i < length; ++i) {
    int index_i=i & 1;
    int index_i_1=1 - index_i;
    Vertex item=iterator.next();
    cost[index_i]=new double[item.attribute.nature.length];
    double perfect_cost_line=Double.MAX_VALUE;
    int k=0;
    Nature[] curTagSet=item.attribute.nature;
    for (    Nature cur : curTagSet) {
      cost[index_i][k]=Double.MAX_VALUE;
      int j=0;
      for (      Nature p : preTagSet) {
        double now=cost[index_i_1][j] + transformMatrixDictionary.transititon_probability[p.ordinal()][cur.ordinal()] - Math.log((item.attribute.frequency[k] + 1e-8) / transformMatrixDictionary.getTotalFrequency(cur.ordinal()));
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
    preItem.confirmNature(pre);
    preTagSet=curTagSet;
    preItem=item;
  }
}
