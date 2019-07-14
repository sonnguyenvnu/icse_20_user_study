private void cleanupNonces(){
  long now=System.currentTimeMillis() / 1000;
  if (now - lastCleaned > 1) {
    Iterator<NonceEntry> iterator=NONCES.iterator();
    while (iterator.hasNext()) {
      NonceEntry nextNonce=iterator.next();
      long difference=now - nextNonce.timestamp;
      if (difference > getValidityWindowSeconds()) {
        iterator.remove();
      }
 else {
        break;
      }
    }
    lastCleaned=now;
  }
}
