@Override @SuppressWarnings("unchecked") public <T>T create(Class<T> aClass){
  if (BaseProjectPlugin.class == aClass) {
    return (T)createProjectPlugin();
  }
 else   if (BaseApplicationPlugin.class == aClass) {
    return (T)createApplicationPlugin();
  }
  throw new IllegalArgumentException("Can't create instance: " + aClass);
}
