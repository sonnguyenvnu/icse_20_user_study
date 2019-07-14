private static void invalidateInternal(Section section){
  section.setInvalidated(true);
  if (section.getParent() != null) {
    invalidateInternal(section.getParent());
  }
}
