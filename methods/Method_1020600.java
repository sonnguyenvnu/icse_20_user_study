public Iterator<Partition> iterator(){
  final Iterator<Map.Entry<Integer,Broker>> iterator=partitionMap.entrySet().iterator();
  return new Iterator<Partition>(){
    public boolean hasNext(){
      return iterator.hasNext();
    }
    public Partition next(){
      Map.Entry<Integer,Broker> next=iterator.next();
      return new Partition(next.getValue(),next.getKey());
    }
    public void remove(){
      iterator.remove();
    }
  }
;
}
