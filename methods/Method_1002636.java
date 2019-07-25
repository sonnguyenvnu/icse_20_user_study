@Setup public void start() throws Exception {
  setUp();
  okhttpChannel=OkHttpChannelBuilder.forAddress("127.0.0.1",port()).usePlaintext().directExecutor().build();
  githubApiOkhttpClient=GithubServiceGrpc.newBlockingStub(okhttpChannel);
  githubApiOkhttpFutureClient=GithubServiceGrpc.newFutureStub(okhttpChannel);
}
