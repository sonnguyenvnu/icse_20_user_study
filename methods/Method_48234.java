public void commit(){
  for (  Map.Entry<String,Object> entry : writtenValues.entrySet()) {
    if (config instanceof ConcurrentWriteConfiguration && readValues.containsKey(entry.getKey())) {
      ((ConcurrentWriteConfiguration)config).set(entry.getKey(),entry.getValue(),readValues.get(entry.getKey()));
    }
 else {
      config.set(entry.getKey(),entry.getValue());
    }
  }
  rollback();
}
