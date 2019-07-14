@Query("sayHello") HelloReply sayHello(HelloRequest request,StreamingGreeterGrpc.StreamingGreeterStub client,DataFetchingEnvironment dataFetchingEnvironment){
  client.sayHelloStreaming(request,new GraphQlStreamObserver<HelloReply,GraphQlResponse>(dataFetchingEnvironment){
    @Override protected GraphQlResponse getData(    HelloReply value,    ListValue path){
      QueryType data=QueryType.newBuilder().setHelloReply(io.grpc.examples.graphql.HelloReply.newBuilder().setMessage(value.getMessage()).build()).build();
      return GraphQlResponse.newBuilder().setPath(path).setData(data).build();
    }
  }
);
  return null;
}
