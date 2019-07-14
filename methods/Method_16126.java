private Map<String,String> getMapping(){
  return ThreadLocalUtils.get(DefaultTableSwitcher.class.getName() + "_current",HashMap::new);
}
