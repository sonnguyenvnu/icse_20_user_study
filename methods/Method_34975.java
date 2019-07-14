/** 
 * Sets the failed result of this  {@code Future} unless this {@code Future} has already beencancelled or set (including  {@linkplain #setFuture set asynchronously}). When a call to this method returns, the  {@code Future} is guaranteed to be {@linkplain #isDone done} <b>only if</b>the call was accepted (in which case it returns  {@code true}). If it returns  {@code false}, the {@code Future} may have previously been set asynchronously, in which case its result may not beknown yet. That result, though not yet known, cannot by overridden by a call to a  {@code set*}method, only by a call to  {@link #cancel}.
 * @param throwable the exception to be used as the failed result
 * @return true if the attempt was accepted, completing the {@code Future}
 */
protected boolean setException(Throwable throwable){
  Object valueToSet=new Failure(Preconditions.checkNotNull(throwable));
  if (ATOMIC_HELPER.casValue(this,null,valueToSet)) {
    complete();
    return true;
  }
  return false;
}
