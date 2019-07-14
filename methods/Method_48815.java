M getMessage(long vertexId,MessageScope scope){
  return get(vertexId,false).getMessage(normalizeScope(scope),previousScopes);
}
