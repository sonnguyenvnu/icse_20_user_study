@Override public int hash(Request request){
  return ThreadLocalRandom.current().nextInt();
}
