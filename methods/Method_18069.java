private static int getBorderColor(Component component){
  return isHostSpec(component) ? MOUNT_BORDER_COLOR_HOST : MOUNT_BORDER_COLOR;
}
