public static boolean isSectionDirty(@Nullable Section previousSection,@Nullable Section currentSection){
  if (previousSection == null || currentSection == null) {
    return true;
  }
  return currentSection.shouldComponentUpdate(previousSection,currentSection);
}
