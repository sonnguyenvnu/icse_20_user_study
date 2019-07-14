/** 
 * Called when the client side  {@link IBinder} for this {@link CustomTabsSessionToken} is dead.Can also be used to clean up  {@link DeathRecipient} instances allocated for the given token.
 * @param sessionToken The session token for which the {@link DeathRecipient} call has beenreceived.
 * @return Whether the clean up was successful. Multiple calls with two tokens holdings thesame binder will return false.
 */
protected boolean cleanUpSession(CustomTabsSessionToken sessionToken){
  try {
synchronized (mDeathRecipientMap) {
      IBinder binder=sessionToken.getCallbackBinder();
      DeathRecipient deathRecipient=mDeathRecipientMap.get(binder);
      binder.unlinkToDeath(deathRecipient,0);
      mDeathRecipientMap.remove(binder);
    }
  }
 catch (  NoSuchElementException e) {
    return false;
  }
  return true;
}
