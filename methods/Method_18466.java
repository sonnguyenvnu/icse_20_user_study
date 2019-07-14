public @Nullable String resolveStringRes(@StringRes int resId,Object... formatArgs){
  return resId != 0 ? mResources.getString(resId,formatArgs) : null;
}
