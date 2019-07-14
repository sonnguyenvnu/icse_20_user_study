/** 
 * {@link #KEY_FOLDERS_FIRST_QUERY}
 */
public static boolean isFirstQueryFolders(Context context){
  return preferences(context).getBoolean(KEY_FOLDERS_FIRST_QUERY,true);
}
