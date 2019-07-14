public Map<String,JedisPool> getNodes(){
  r.lock();
  try {
    return new HashMap<String,JedisPool>(nodes);
  }
  finally {
    r.unlock();
  }
}
