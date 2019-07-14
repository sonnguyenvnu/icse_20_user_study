private @Nullable String keyPathComponentForCount(final int count){
  if (count == 0) {
    return "zero";
  }
 else   if (count == 1) {
    return "one";
  }
 else   if (count == 2) {
    return "two";
  }
 else   if (count > 2 && count <= 5) {
    return "few";
  }
 else   if (count > 5) {
    return "many";
  }
  return null;
}
