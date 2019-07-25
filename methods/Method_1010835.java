public void refresh(String path){
synchronized (LOCK) {
    created.add(path);
  }
}
