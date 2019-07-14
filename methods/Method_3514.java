/** 
 * dijkstra????
 * @param graph
 * @return
 */
private static List<Vertex> dijkstra(Graph graph){
  List<Vertex> resultList=new LinkedList<Vertex>();
  Vertex[] vertexes=graph.getVertexes();
  List<EdgeFrom>[] edgesTo=graph.getEdgesTo();
  double[] d=new double[vertexes.length];
  Arrays.fill(d,Double.MAX_VALUE);
  d[d.length - 1]=0;
  int[] path=new int[vertexes.length];
  Arrays.fill(path,-1);
  PriorityQueue<State> que=new PriorityQueue<State>();
  que.add(new State(0,vertexes.length - 1));
  while (!que.isEmpty()) {
    State p=que.poll();
    if (d[p.vertex] < p.cost)     continue;
    for (    EdgeFrom edgeFrom : edgesTo[p.vertex]) {
      if (d[edgeFrom.from] > d[p.vertex] + edgeFrom.weight) {
        d[edgeFrom.from]=d[p.vertex] + edgeFrom.weight;
        que.add(new State(d[edgeFrom.from],edgeFrom.from));
        path[edgeFrom.from]=p.vertex;
      }
    }
  }
  for (int t=0; t != -1; t=path[t]) {
    resultList.add(vertexes[t]);
  }
  return resultList;
}
