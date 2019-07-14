/** 
 * Synchronous call to retrieve the last calculated bucket without waiting for any emissions
 * @return last calculated bucket
 */
public Output getLatest(){
  startCachingStreamValuesIfUnstarted();
  if (counterSubject.hasValue()) {
    return counterSubject.getValue();
  }
 else {
    return getEmptyOutputValue();
  }
}
