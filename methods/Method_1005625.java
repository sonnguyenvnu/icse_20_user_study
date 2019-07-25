/** 
 * Interns a call site into this instance. This method is synchronized as it is called during class file translation which runs concurrently on a per class basis.
 * @param cstRef
 */
public synchronized void intern(CstCallSiteRef cstRef){
  if (cstRef == null) {
    throw new NullPointerException("cstRef");
  }
  throwIfPrepared();
  CallSiteIdItem result=callSiteIds.get(cstRef);
  if (result == null) {
    result=new CallSiteIdItem(cstRef);
    callSiteIds.put(cstRef,result);
  }
}
