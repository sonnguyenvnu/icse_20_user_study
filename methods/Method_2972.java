/** 
 * ????id
 * @param word
 * @return
 */
public int[] clusterId(String word){
  int[] ids=new int[3];
  ids[0]=-100;
  ids[1]=-100;
  ids[2]=-100;
  if (brownFullClusters.containsKey(word))   ids[0]=brownFullClusters.get(word);
  if (ids[0] > 0) {
    ids[1]=brown4Clusters.get(ids[0]);
    ids[2]=brown6Clusters.get(ids[0]);
  }
  return ids;
}
