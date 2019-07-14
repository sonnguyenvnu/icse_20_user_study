@Override public String getName(){
  return String.format("%s.%s",getClass().getSimpleName(),this.keyspace);
}
