/** 
 * Builds an  {@link Intent} for adding an action to be executed by the service.
 * @param context A {@link Context}.
 * @param clazz The concrete download service being targeted by the intent.
 * @param downloadAction The action to be executed.
 * @param foreground Whether this intent will be used to start the service in the foreground.
 * @return Created Intent.
 */
public static Intent buildAddActionIntent(Context context,Class<? extends DownloadService> clazz,DownloadAction downloadAction,boolean foreground){
  return getIntent(context,clazz,ACTION_ADD).putExtra(KEY_DOWNLOAD_ACTION,downloadAction.toByteArray()).putExtra(KEY_FOREGROUND,foreground);
}
