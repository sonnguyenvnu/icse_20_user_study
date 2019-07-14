/** 
 * ??????
 * @param from ??
 * @param to ??
 * @param weight ??
 */
public void connect(int from,int to,double weight){
  edgesTo[to].add(new EdgeFrom(from,weight,vertexes[from].word + '@' + vertexes[to].word));
}
