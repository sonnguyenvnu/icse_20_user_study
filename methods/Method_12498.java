protected Info convertInfo(Instance instance,Throwable ex){
  log.warn("Couldn't retrieve info for {}",instance,ex);
  return Info.empty();
}
