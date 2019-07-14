@Override public void createSchema(){
  final JanusGraphManagement management=getJanusGraph().openManagement();
  try {
    if (management.getRelationTypes(RelationType.class).iterator().hasNext()) {
      management.rollback();
      return;
    }
    LOGGER.info("creating schema");
    createProperties(management);
    createVertexLabels(management);
    createEdgeLabels(management);
    createCompositeIndexes(management);
    createMixedIndexes(management);
    management.commit();
  }
 catch (  Exception e) {
    management.rollback();
  }
}
