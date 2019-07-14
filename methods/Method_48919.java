@Override public boolean hasNext(){
  if (iterator == null)   iterator=getNewIterator(currentLimit);
  if (count < currentLimit)   return iterator.hasNext();
  if (currentLimit >= maxLimit)   return false;
  currentLimit=(int)Math.min(maxLimit,Math.round(currentLimit * 2.0));
  iterator=getNewIterator(currentLimit);
  for (int i=0; i < count; i++)   iterator.next();
  assert count < currentLimit : count + " vs " + currentLimit + " | " + maxLimit;
  return hasNext();
}
