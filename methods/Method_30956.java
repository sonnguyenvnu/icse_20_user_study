@NonNull public static <E>Iterator<E> asIterator(@NonNull Enumeration<E> enumeration){
  return new Iterator<E>(){
    @Override public boolean hasNext(){
      return enumeration.hasMoreElements();
    }
    @Override public E next(){
      return enumeration.nextElement();
    }
  }
;
}
