public static boolean sizeLargerOrEqualThan(Iterable i,int limit){
  if (i instanceof Collection)   return ((Collection)i).size() >= limit;
  Iterator iterator=i.iterator();
  int count=0;
  while (iterator.hasNext()) {
    iterator.next();
    count++;
    if (count >= limit)     return true;
  }
  return false;
}
