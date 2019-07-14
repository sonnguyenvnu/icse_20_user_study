/** 
 * Expires entries in the access-order queue. 
 */
@GuardedBy("evictionLock") void expireAfterAccessEntries(long now){
  if (!expiresAfterAccess()) {
    return;
  }
  expireAfterAccessEntries(accessOrderWindowDeque(),now);
  if (evicts()) {
    expireAfterAccessEntries(accessOrderProbationDeque(),now);
    expireAfterAccessEntries(accessOrderProtectedDeque(),now);
  }
}
