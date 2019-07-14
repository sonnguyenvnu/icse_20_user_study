static boolean isLayoutSpec(@Nullable Component component){
  return (component != null && component.getMountType() == MountType.NONE);
}
