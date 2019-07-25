@SuppressWarnings("unchecked") void remove(InnerSubscription<T> inner){
  for (; ; ) {
    InnerSubscription<T>[] a=subscribers.get();
    int n=a.length;
    if (n == 0) {
      return;
    }
    int j=-1;
    for (int i=0; i < n; i++) {
      if (a[i] == inner) {
        j=i;
        break;
      }
    }
    if (j < 0) {
      return;
    }
    InnerSubscription<T>[] b;
    if (n == 1) {
      b=EMPTY;
    }
 else {
      b=new InnerSubscription[n - 1];
      System.arraycopy(a,0,b,0,j);
      System.arraycopy(a,j + 1,b,j,n - j - 1);
    }
    if (subscribers.compareAndSet(a,b)) {
      break;
    }
  }
}
