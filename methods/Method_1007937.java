private void request(final String method,final WebTarget resource,final Invocation.Builder request) throws DockerException, InterruptedException {
  try {
    headers(request).async().method(method,String.class).get();
  }
 catch (  ExecutionException|MultiException e) {
    throw propagate(method,resource,e);
  }
}
