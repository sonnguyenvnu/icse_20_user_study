/** 
 * Obtain a  {@link CustomTabsSessionToken} from an intent. See {@link CustomTabsIntent.Builder}for ways to generate an intent for custom tabs.
 * @param intent The intent to generate the token from. This has to include an extra for{@link CustomTabsIntent#EXTRA_SESSION}.
 * @return The token that was generated.
 */
public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent){
  Bundle b=intent.getExtras();
  IBinder binder=BundleCompat.getBinder(b,CustomTabsIntent.EXTRA_SESSION);
  if (binder == null)   return null;
  return new CustomTabsSessionToken(ICustomTabsCallback.Stub.asInterface(binder));
}
