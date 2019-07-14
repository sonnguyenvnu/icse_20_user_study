@Override @NonNull public List<IconDataParcelable> getPreloadItems(int position){
  IconDataParcelable iconData=urisToLoad.get(position);
  if (iconData == null)   return Collections.emptyList();
  return Collections.singletonList(iconData);
}
