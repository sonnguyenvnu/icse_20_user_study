/** 
 * {@link #KEY_FOLDERS_FIRST_QUERY}
 */
public static void reportFirstQueryFolders(Context context){
  edit(context).putBoolean(KEY_FOLDERS_FIRST_QUERY,false).commit();
}
