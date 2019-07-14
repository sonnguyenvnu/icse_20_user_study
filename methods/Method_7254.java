/** 
 * Provides browsers a way to generate a dummy  {@link CustomTabsSession} for testingpurposes.
 * @param componentName The component the session should be created for.
 * @return A dummy session with no functionality.
 */
public static CustomTabsSession createDummySessionForTesting(ComponentName componentName){
  return new CustomTabsSession(null,new CustomTabsSessionToken.DummyCallback(),componentName);
}
