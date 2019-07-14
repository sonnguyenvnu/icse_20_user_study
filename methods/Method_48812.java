private static void verifyIncidentTraversal(FulgoraElementTraversal<Vertex,Edge> traversal){
  List<Step> steps=traversal.getSteps();
  Step<Vertex,?> startStep=steps.get(0);
  Preconditions.checkArgument(startStep instanceof JanusGraphVertexStep && JanusGraphTraversalUtil.isEdgeReturnStep((JanusGraphVertexStep)startStep),"Expected first step to be an edge step but found: %s",startStep);
  Optional<Step> violatingStep=steps.stream().filter(s -> !(s instanceof JanusGraphVertexStep || s instanceof OrderGlobalStep || s instanceof OrderLocalStep || s instanceof IdentityStep || s instanceof FilterStep)).findAny();
  violatingStep.ifPresent(step -> {
    throw new IllegalArgumentException("Encountered unsupported step in incident traversal: " + step);
  }
);
}
