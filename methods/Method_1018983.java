/** 
 * @return Dummy vertex used as a target.Forces an exhaustive search since it can never be matched.
 */
private Vertex<ClassNode> dummy(){
  return new ClassVertex(null,new ClassNode()){
    @Override public Set<Edge<ClassNode>> getEdges(){
      return Collections.emptySet();
    }
  }
;
}
