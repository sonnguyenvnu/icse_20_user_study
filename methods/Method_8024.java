@Override public void invalidateDrawable(Drawable who){
  if (who == translationDrawable || who == Theme.dialogs_archiveAvatarDrawable) {
    invalidate(who.getBounds());
  }
 else {
    super.invalidateDrawable(who);
  }
}
