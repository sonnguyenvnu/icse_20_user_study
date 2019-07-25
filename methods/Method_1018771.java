@Override public void clear(){
  for (E e=first; e != null; ) {
    E next=e.getNext();
    e.setPrevious(null);
    e.setNext(null);
    e=next;
  }
  first=last=null;
}
