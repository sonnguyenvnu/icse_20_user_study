@Override public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder){
  httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
  return httpClientBuilder;
}
