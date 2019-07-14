@Override public @Nullable @AccessibilityRoleType String getAccessibilityRole(){
  return (mOtherFlags & OFLAG_HAS_ACCESSIBILITY_ROLE) != 0 ? (String)getOrCreateObjectProps().get(INDEX_AccessibilityRole) : null;
}
