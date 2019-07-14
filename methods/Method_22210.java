/** 
 * Returns the current Configuration for this application.
 * @param context Context for the application being reported.
 * @return A String representation of the current configuration for the application.
 */
@Nullable private JSONObject collectConfiguration(@NonNull Context context){
  try {
    return configToJson(context.getResources().getConfiguration());
  }
 catch (  RuntimeException e) {
    ACRA.log.w(LOG_TAG,"Couldn't retrieve CrashConfiguration for : " + context.getPackageName(),e);
    return null;
  }
}
