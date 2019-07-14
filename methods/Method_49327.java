public int[] getAll(){
  KeysContainer keys=keys();
  int[] all=new int[keys.size()];
  Iterator<IntCursor> iterator=keys.iterator();
  int pos=0;
  while (iterator.hasNext())   all[pos++]=iterator.next().value;
  return all;
}
