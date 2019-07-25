@Override public Iterator<E> keys(final boolean up){
synchronized (this.map) {
    final TreeMap<Integer,Set<E>> m=new TreeMap<Integer,Set<E>>();
    Set<E> s;
    for (    final Map.Entry<E,AtomicInteger> entry : this.map.entrySet()) {
      s=m.get(entry.getValue().intValue());
      if (s == null) {
        s=this.map instanceof TreeMap ? new TreeSet<E>(((TreeMap<E,AtomicInteger>)this.map).comparator()) : new HashSet<E>();
        s.add(entry.getKey());
        m.put(entry.getValue().intValue(),s);
      }
 else {
        s.add(entry.getKey());
      }
    }
    final List<E> l=new ArrayList<E>(this.map.size());
    for (    final Set<E> f : m.values()) {
      for (      final E e : f)       l.add(e);
    }
    if (up)     return l.iterator();
    final List<E> r=new ArrayList<E>(l.size());
    for (int i=l.size() - 1; i >= 0; i--)     r.add(l.get(i));
    return r.iterator();
  }
}
