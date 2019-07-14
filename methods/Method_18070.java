private static int getCornerColor(Component component){
  return isHostSpec(component) ? MOUNT_CORNER_COLOR_HOST : MOUNT_CORNER_COLOR;
}
