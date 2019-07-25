@SuppressWarnings("unchecked") void remove(RefCountSubscriber<T> rcs){
  for (; ; ) {
    RefCountSubscriber<T>[] a=subscribers.get();
    int n=a.length;
    if (n == 0) {
      break;
    }
    int j=-1;
    for (int i=0; i < n; i++) {
      if (rcs == a[i]) {
        j=i;
        break;
      }
    }
    if (j < 0) {
      break;
    }
    RefCountSubscriber<T>[] b;
    if (n == 1) {
      b=TERMINATED;
    }
 else {
      b=new RefCountSubscriber[n - 1];
      System.arraycopy(a,0,b,0,j);
      System.arraycopy(a,j + 1,b,j,n - j - 1);
    }
    if (subscribers.compareAndSet(a,b)) {
      if (b == TERMINATED) {
        cancel();
      }
      break;
    }
  }
}
