public void put(String key,String value){
  getKeyBinding(key).ifPresent(binding -> put(binding,value));
}
