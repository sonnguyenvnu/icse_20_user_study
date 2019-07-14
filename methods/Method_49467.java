/** 
 * Creates the edge labels.
 */
protected void createEdgeLabels(final JanusGraphManagement management){
  management.makeEdgeLabel("father").multiplicity(Multiplicity.MANY2ONE).make();
  management.makeEdgeLabel("mother").multiplicity(Multiplicity.MANY2ONE).make();
  management.makeEdgeLabel("lives").signature(management.getPropertyKey("reason")).make();
  management.makeEdgeLabel("pet").make();
  management.makeEdgeLabel("brother").make();
  management.makeEdgeLabel("battled").make();
}
