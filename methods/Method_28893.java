protected <T>Response<T> getResponse(Builder<T> builder){
  Response<T> lr=new Response<T>(builder);
  pipelinedResponses.add(lr);
  return lr;
}
