protected List<RedisTbl> inter(List<Pair<String,Integer>> userGiven,List<RedisTbl> redisTbls){
  List<RedisTbl> result=new LinkedList<>();
  redisTbls.forEach(new Consumer<RedisTbl>(){
    @Override public void accept(    RedisTbl redisTbl){
      boolean exist=false;
      for (      Pair<String,Integer> addr : userGiven) {
        if (addr.getKey().equalsIgnoreCase(redisTbl.getRedisIp()) && addr.getValue().equals(redisTbl.getRedisPort())) {
          exist=true;
          break;
        }
      }
      if (exist) {
        result.add(redisTbl);
      }
    }
  }
);
  return result;
}
