public List<T> sort(){
  Comparator<T> sourceIndependent=(o1,o2) -> {
    int r1=o1.compareTo(o2);
    int r2=o2.compareTo(o1);
    return Math.max(r1,-r2);
  }
;
  MultiMap<T,T> greater=new SetBasedMultiMap<>();
  for (  T item1 : myItems) {
    for (    T item2 : myItems) {
      if (sourceIndependent.compare(item1,item2) > 0) {
        greater.putValue(item1,item2);
      }
    }
  }
{
    boolean changed;
    do {
      changed=false;
      for (      T item1 : myItems) {
        for (        T item2 : new ArrayList<>(greater.get(item1))) {
          Collection<T> toAdd=greater.get(item2);
          changed|=!greater.get(item1).containsAll(toAdd);
          greater.putValues(item1,toAdd);
        }
      }
    }
 while (changed);
  }
  ArrayList<T> items=new ArrayList<>(myItems);
  items.sort(Comparator.comparing(OrderParticipant::getId));
  ArrayList<T> sorted=new ArrayList<>();
  while (!items.isEmpty()) {
    boolean changed=false;
    for (    T candidate : items) {
      if (sorted.containsAll(greater.get(candidate))) {
        sorted.add(candidate);
        items.remove(candidate);
        changed=true;
        break;
      }
    }
    if (!changed) {
      LOG.error("aspects sorting conflict: " + items);
      sorted.addAll(items);
      items.clear();
    }
  }
  return sorted;
}
