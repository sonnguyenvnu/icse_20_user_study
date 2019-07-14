@Override public <C extends Configuration<K,V>>C getConfiguration(Class<C> clazz){
  if (clazz.isInstance(configuration)) {
    return clazz.cast(configuration);
  }
  throw new IllegalArgumentException("The configuration class " + clazz + " is not supported by this implementation");
}
