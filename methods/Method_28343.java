@Override public void onRepoStarred(boolean isStarred){
  starRepoImage.setImageResource(isStarred ? R.drawable.ic_star_filled : R.drawable.ic_star);
  starRepoImage.tintDrawableColor(isStarred ? accentColor : iconColor);
  onEnableDisableStar(true);
}
