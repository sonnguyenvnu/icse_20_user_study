@Override public <E extends TaggedLogAPIEntity>Future<GenericServiceAPIResponseEntity<String>> _XXXXX_(final List<E> entities) throws IOException, EagleServiceClientException {
  return this.client.getJerseyClient().getExecutorService().submit(new Callable<GenericServiceAPIResponseEntity<String>>(){
    @Override public GenericServiceAPIResponseEntity<String> call() throws Exception {
      return client.create(entities);
    }
  }
);
}