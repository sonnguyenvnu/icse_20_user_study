@Override public void setAccessibilityRole(@Nullable @AccessibilityRoleType String role){
  mPrivateFlags|=PFLAG_ACCESSIBILITY_ROLE_IS_SET;
  if (role == null) {
    mOtherFlags&=~OFLAG_HAS_ACCESSIBILITY_ROLE;
  }
 else {
    mOtherFlags|=OFLAG_HAS_ACCESSIBILITY_ROLE;
  }
  getOrCreateObjectProps().append(INDEX_AccessibilityRole,role);
}
