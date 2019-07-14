private boolean isMountedHostWithChildContent(MountItem mountItem){
  if (mountItem == null) {
    return false;
  }
  final Object content=mountItem.getContent();
  if (!(content instanceof ComponentHost)) {
    return false;
  }
  final ComponentHost host=(ComponentHost)content;
  return host.getMountItemCount() > 0;
}
