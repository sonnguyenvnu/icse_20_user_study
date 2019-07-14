private static void makeAccessible(AccessibleObject ao){
  if (ao instanceof Member) {
    Member member=(Member)ao;
    if (!Modifier.isPublic(member.getModifiers())) {
      ao.setAccessible(true);
    }
  }
}
