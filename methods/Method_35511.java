public RequestSpec requestSpecForTask(final Class<? extends AdminTask> taskClass){
  RequestSpec requestSpec=tryFind(routes.entrySet(),new Predicate<Map.Entry<RequestSpec,AdminTask>>(){
    @Override public boolean apply(    Map.Entry<RequestSpec,AdminTask> input){
      return input.getValue().getClass().equals(taskClass);
    }
  }
).transform(new Function<Map.Entry<RequestSpec,AdminTask>,RequestSpec>(){
    @Override public RequestSpec apply(    Map.Entry<RequestSpec,AdminTask> input){
      return input.getKey();
    }
  }
).orNull();
  if (requestSpec == null) {
    throw new NotFoundException("No route could be found for " + taskClass.getSimpleName());
  }
  return requestSpec;
}
