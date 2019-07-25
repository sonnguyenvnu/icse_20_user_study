public static List<Result> search(Parameter... params){
  Set<Result> results=Collections.newSetFromMap(new ConcurrentHashMap<>());
  Map<String,ClassNode> nodes=Input.get().getClasses();
  ExecutorService pool=Threads.pool();
  for (  Entry<String,ClassNode> entry : nodes.entrySet()) {
    for (    Parameter param : params) {
      if (skip(param.getSkipList(),entry.getKey())) {
        continue;
      }
      pool.submit(() -> {
        ClassNode cn=entry.getValue();
        searchClass(cn,param,results);
        for (        MethodNode mn : cn.methods) {
          searchMethodInstructions(cn,mn,param,results);
        }
      }
);
    }
  }
  Threads.waitForCompletion(pool);
  return new ArrayList<>(results);
}
