@Override public void onGistStarred(boolean isStarred){
  startGist.setImageResource(isStarred ? R.drawable.ic_star_filled : R.drawable.ic_star);
  startGist.tintDrawableColor(isStarred ? accentColor : iconColor);
  startGist.setEnabled(true);
}
