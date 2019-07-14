@Override public SingleServedStubResult getServedStub(UUID id){
  return executeRequest(adminRoutes.requestSpecForTask(GetServedStubTask.class),PathParams.single("id",id),SingleServedStubResult.class);
}
