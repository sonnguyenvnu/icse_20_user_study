public final void append(Vector<E> v){
  if (v.size + size > capacity) {
    Object neo[]=new Object[capacity + v.capacity];
    capacity+=v.capacity;
    System.arraycopy(info,0,neo,0,size);
    info=neo;
  }
  System.arraycopy(v.info,0,info,size,v.size);
  size+=v.size;
}
