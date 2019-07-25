/** 
 * Pushes a new object onto the queue l
 * @param l linked list queue of Matrix obj's
 * @param toadd matrix to push onto queue
 */
private void push(LinkedList l,double[] toadd){
  assert (l.size() <= m);
  if (l.size() == m) {
    double[] last=(double[])l.get(0);
    System.arraycopy(toadd,0,last,0,toadd.length);
    Object ptr=last;
    for (int i=0; i < l.size() - 1; i++) {
      l.set(i,(double[])l.get(i + 1));
    }
    l.set(m - 1,ptr);
  }
 else {
    double[] newArray=new double[toadd.length];
    System.arraycopy(toadd,0,newArray,0,toadd.length);
    l.addLast(newArray);
  }
}
