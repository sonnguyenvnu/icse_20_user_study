public void set(Entity<?> en){
synchronized (map) {
    this.map.put(en.getType(),en);
  }
}
