/** 
 * Creates the vertex labels.
 */
protected void createVertexLabels(final JanusGraphManagement management){
  management.makeVertexLabel("titan").make();
  management.makeVertexLabel("location").make();
  management.makeVertexLabel("god").make();
  management.makeVertexLabel("demigod").make();
  management.makeVertexLabel("human").make();
  management.makeVertexLabel("monster").make();
}
