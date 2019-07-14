void sendMessage(long vertexId,M message,MessageScope scope){
  VertexState<M> state=get(vertexId,true);
  if (scope instanceof MessageScope.Global)   state.addMessage(message,GLOBAL_SCOPE,currentScopes,combiner);
 else   state.setMessage(message,scope,currentScopes);
}
