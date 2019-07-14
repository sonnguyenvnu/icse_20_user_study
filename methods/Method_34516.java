private static String getAccessLevel(int modifiers){
  if (Modifier.isPublic(modifiers)) {
    return "public";
  }
 else   if (Modifier.isProtected(modifiers)) {
    return "protected";
  }
 else   if (Modifier.isPrivate(modifiers)) {
    return "private";
  }
 else {
    return "default";
  }
}
