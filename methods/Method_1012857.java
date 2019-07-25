private static String refresh(JobMTask task,List<String> addressList){
  if (System.currentTimeMillis() > CACHE_VALID_ENABLE) {
    CACHE_VALID_INSTANCES.clear();
    CACHE_VALID_ENABLE=System.currentTimeMillis() + 1000 * 60 * 60 * 24;
  }
  if (!CACHE_VALID_INSTANCES.containsKey(task.getTaskKey())) {
    CACHE_VALID_INSTANCES.put(task.getTaskKey(),addressList);
    CACHE_VALID_COUNT.put(task.getTaskKey(),0);
  }
  if (CACHE_VALID_COUNT.get(task.getTaskKey()) < MAX) {
    Integer integer=CACHE_VALID_COUNT.get(task.getTaskKey());
    CACHE_VALID_COUNT.put(task.getTaskKey(),++integer);
    return task.getCurrentHandler();
  }
  List<String> list=CACHE_VALID_INSTANCES.get(task.getTaskKey());
  list.remove(task.getCurrentHandler());
  if (list.size() > 0) {
    return list.get(new Random().nextInt(list.size()));
  }
  CACHE_VALID_COUNT.remove(task.getTaskKey());
  CACHE_VALID_INSTANCES.remove(task.getTaskKey());
  return null;
}
