/** 
 * Initializes loader with given `Context`. Subsequent calls should have no effect since application Context is retrieved. Libraries will not be loaded immediately but only when needed.
 * @param context any Context except null
 */
public static void initialize(@NonNull final Context context){
  sAppContext=context.getApplicationContext();
}
