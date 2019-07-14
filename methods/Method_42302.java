/** 
 * Shows the selection dialog for a given renderer.
 * @param activity The parent activity.
 * @param title The dialog's title.
 * @param trackInfo The current track information.
 * @param rendererIndex The index of the renderer.
 */
public void showSelectionDialog(Activity activity,CharSequence title,MappedTrackInfo trackInfo,int rendererIndex){
  this.trackInfo=trackInfo;
  this.rendererIndex=rendererIndex;
  trackGroups=trackInfo.getTrackGroups(rendererIndex);
  trackGroupsAdaptive=new boolean[trackGroups.length];
  for (int i=0; i < trackGroups.length; i++) {
    trackGroupsAdaptive[i]=adaptiveVideoTrackSelectionFactory != null && trackInfo.getAdaptiveSupport(rendererIndex,i,false) != RendererCapabilities.ADAPTIVE_NOT_SUPPORTED && trackGroups.get(i).length > 1;
  }
  isDisabled=selector.getRendererDisabled(rendererIndex);
  override=selector.getSelectionOverride(rendererIndex,trackGroups);
  AlertDialog.Builder builder=new AlertDialog.Builder(activity,themeHelper.getDialogStyle());
  builder.setTitle(title).setView(buildView(LayoutInflater.from(builder.getContext()))).setPositiveButton(android.R.string.ok,this).setNegativeButton(android.R.string.cancel,null).create().show();
}
