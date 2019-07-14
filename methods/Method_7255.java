/** 
 * This sets the action button on the toolbar with ID {@link CustomTabsIntent#TOOLBAR_ACTION_BUTTON_ID}.
 * @param icon          The new icon of the action button.
 * @param description   Content description of the action button.
 * @see CustomTabsSession#setToolbarItem(int,Bitmap,String)
 */
public boolean setActionButton(@NonNull Bitmap icon,@NonNull String description){
  Bundle bundle=new Bundle();
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
