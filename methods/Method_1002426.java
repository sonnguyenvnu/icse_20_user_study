/** 
 * Release a reference which decrements the reference count.
 */
void release(){
  _refCount.decrementAndGet();
}
