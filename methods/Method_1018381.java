public static synchronized String next(){
  return String.format("%d-%04d-%07d",Id.getWorkerId(),strategy.prefix(),strategy.next());
}
