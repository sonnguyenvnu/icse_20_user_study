private static FulgoraElementTraversal<Vertex,Edge> getReverseTraversal(final MessageScope.Local<?> scope,final JanusGraphTransaction graph,@Nullable final Vertex start){
  Traversal.Admin<Vertex,Edge> incident=scope.getIncidentTraversal().get().asAdmin();
  FulgoraElementTraversal<Vertex,Edge> result=FulgoraElementTraversal.of(graph);
  for (  Step step : incident.getSteps())   result.addStep(step);
  Step<Vertex,?> startStep=result.getStartStep();
  assert startStep instanceof VertexStep;
  ((VertexStep)startStep).reverseDirection();
  if (start != null)   result.addStep(0,new StartStep<>(incident,start));
  result.asAdmin().setStrategies(FULGORA_STRATEGIES);
  return result;
}
