M getAggregateMessage(long vertexId,MessageScope scope){
  return getPartitioned(vertexId).getMessage(normalizeScope(scope),previousScopes);
}
