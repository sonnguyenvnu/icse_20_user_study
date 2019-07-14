public long getValue(String name){
synchronized (mutex) {
    Long value=counterValues.get(name);
    if (value == null) {
      return 0;
    }
    return value;
  }
}
