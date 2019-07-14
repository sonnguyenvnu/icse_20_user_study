private boolean notNull(Properties props,String prefix,String name){
  return props.getProperty(prefix + name) != null;
}
