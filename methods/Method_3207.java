public Map<String,Double> allTf(){
  Map<String,Double> result=new HashMap<String,Double>();
  for (  Map<String,Double> d : tfMap.values()) {
    for (    Map.Entry<String,Double> tf : d.entrySet()) {
      Double f=result.get(tf.getKey());
      if (f == null) {
        result.put(tf.getKey(),tf.getValue());
      }
 else {
        result.put(tf.getKey(),f + tf.getValue());
      }
    }
  }
  return result;
}
