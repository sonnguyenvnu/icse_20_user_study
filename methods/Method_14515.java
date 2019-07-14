@Override public String idFromValueAndType(Object instance,Class<?> type){
  return ReconConfig.s_opClassToName.get(type);
}
