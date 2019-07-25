@Override public Settings copy(){
  return new StormSettings(new LinkedHashMap<Object,Object>(cfg));
}
