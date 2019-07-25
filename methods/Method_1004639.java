static void run(){
synchronized (linkedHashMapCaches) {
    Iterator<WeakReference<LinkedHashMapCache>> it=linkedHashMapCaches.iterator();
    while (it.hasNext()) {
      WeakReference<LinkedHashMapCache> ref=it.next();
      LinkedHashMapCache c=ref.get();
      if (c == null) {
        it.remove();
      }
 else {
        c.cleanExpiredEntry();
      }
    }
  }
}
