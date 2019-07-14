@Override public int hashCode(){
  return Objects.hash(hitCount,missCount,loadSuccessCount,loadFailureCount,totalLoadTime,evictionCount,evictionWeight);
}
