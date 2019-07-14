/** 
 * Updates the visuals for toolbar items. Will only succeed if a custom tab created using this session is in the foreground in browser and the given id is valid.
 * @param id            The id for the item to update.
 * @param icon          The new icon of the toolbar item.
 * @param description   Content description of the toolbar item.
 * @return              Whether the update succeeded.
 * @deprecated UseCustomTabsSession#setSecondaryToolbarViews(RemoteViews, int[], PendingIntent)
 */
@Deprecated public boolean setToolbarItem(int id,@NonNull Bitmap icon,@NonNull String description){
  Bundle bundle=new Bundle();
  bundle.putInt(CustomTabsIntent.KEY_ID,id);
  bundle.putParcelable(CustomTabsIntent.KEY_ICON,icon);
  bundle.putString(CustomTabsIntent.KEY_DESCRIPTION,description);
  Bundle metaBundle=new Bundle();
  metaBundle.putBundle(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE,bundle);
  try {
    return mService.updateVisuals(mCallback,metaBundle);
  }
 catch (  RemoteException e) {
    return false;
  }
}
