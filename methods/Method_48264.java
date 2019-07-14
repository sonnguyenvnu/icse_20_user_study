private int persist(final Map<String,Map<StaticBuffer,KCVMutation>> subMutations){
  BackendOperation.execute(new Callable<Boolean>(){
    @Override public Boolean call() throws Exception {
      manager.mutateMany(subMutations,tx);
      return true;
    }
    @Override public String toString(){
      return "CacheMutation";
    }
  }
,maxWriteTime);
  subMutations.clear();
  return 0;
}
