@Override public Iterator<A> iterator(){
  HashMap<A,Boolean> known=new HashMap<>();
  return filter(a -> known.putIfAbsent(a,true) == null,as).iterator();
}
