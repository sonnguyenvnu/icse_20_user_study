public synchronized void setCachedImage(CloseableReference<CloseableImage> cachedImage){
  CloseableReference.closeSafely(mCachedImage);
  mCachedImage=CloseableReference.cloneOrNull(cachedImage);
}
