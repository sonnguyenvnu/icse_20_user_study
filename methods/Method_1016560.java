@SuppressFBWarnings("DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED") synchronized ClassLoader classloader(Serializable key,JarState state){
  SerializedKey serializedKey=new SerializedKey(key);
  return cache.computeIfAbsent(serializedKey,k -> new FeatureClassLoader(state.jarUrls(),this.getClass().getClassLoader()));
}
