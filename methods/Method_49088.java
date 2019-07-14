/** 
 * If this step is followed by a subsequent has step then the properties will need to be known when that has step is executed. The batch property pre-fetching optimisation loads those properties into the vertex cache with a multiQuery preventing the need to go back to the storage back-end for each vertex to fetch the properties.
 * @param traversal The traversal containing the step
 * @param vertexStep The step to potentially apply the optimisation to
 * @param nextStep The next step in the traversal
 * @param txVertexCacheSize The size of the vertex cache
 */
private void applyBatchPropertyPrefetching(final Admin<?,?> traversal,final JanusGraphVertexStep vertexStep,final Step nextStep,final int txVertexCacheSize){
  if (Vertex.class.isAssignableFrom(vertexStep.getReturnClass())) {
    if (HasStepFolder.foldableHasContainerNoLimit(vertexStep)) {
      vertexStep.setBatchPropertyPrefetching(true);
      vertexStep.setTxVertexCacheSize(txVertexCacheSize);
    }
  }
 else   if (nextStep instanceof EdgeVertexStep) {
    EdgeVertexStep edgeVertexStep=(EdgeVertexStep)nextStep;
    if (HasStepFolder.foldableHasContainerNoLimit(edgeVertexStep)) {
      JanusGraphEdgeVertexStep estep=new JanusGraphEdgeVertexStep(edgeVertexStep,txVertexCacheSize);
      TraversalHelper.replaceStep(nextStep,estep,traversal);
    }
  }
}
