@Override public void remove(byte[] key){
  Preconditions.checkNotNull(key,"key in db is not null.");
  db.put(Key.of(key),Value.of(Value.Operator.DELETE,null));
}
