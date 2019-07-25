@Override public Map<String,Object> execute(Map<String,Object> context){
  getDevice(module.getConfiguration());
  getResult(module.getConfiguration());
  return null;
}
