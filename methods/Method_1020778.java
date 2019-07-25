public void delete(Subscription s){
  if (!cancelled) {
synchronized (this) {
      if (!cancelled) {
        OpenHashSet<Subscription> h=set;
        if (h != null) {
          h.remove(s);
        }
      }
    }
  }
}
