private void integrate(HashMap<String,Float> state){
  if (state != null)   state.keySet().forEach(key -> stocks.put(key,state.get(key)));
}
