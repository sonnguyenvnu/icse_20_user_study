@Override public void then(Block block){
  promise.then(v -> block.execute());
}
