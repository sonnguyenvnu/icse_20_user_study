@Override public void setValue(V value){
  requireNonNull(value);
  if (action != Action.CREATED) {
    action=(hasEntry && exists()) ? Action.UPDATED : Action.CREATED;
  }
  this.value=value;
}
