@Override public void accessibilityRole(@Nullable @AccessibilityRole.AccessibilityRoleType String role){
  getOrCreateNodeInfo().setAccessibilityRole(role);
}
