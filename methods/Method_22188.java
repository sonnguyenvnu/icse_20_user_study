/** 
 * <p> Initialize ACRA for a given Application. <p> The call to this method should be placed as soon as possible in the  {@link Application#attachBaseContext(Context)} method.<p> Sends any unsent reports. </p>
 * @param app    Your Application class.
 * @param config CoreConfiguration to manually set up ACRA configuration.
 * @throws IllegalStateException if it is called more than once.
 */
public static void init(@NonNull Application app,@NonNull CoreConfiguration config){
  init(app,config,true);
}
