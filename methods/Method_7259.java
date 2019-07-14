/** 
 * Provides browsers a way to generate a dummy  {@link CustomTabsSessionToken} for testingpurposes.
 * @return A dummy token with no functionality.
 */
public static CustomTabsSessionToken createDummySessionTokenForTesting(){
  return new CustomTabsSessionToken(new DummyCallback());
}
