private static boolean matches(@NotNull ChooseByNameBase base,@NotNull String pattern,@NotNull MinusculeMatcher matcher,@Nullable String name){
  if (name == null) {
    return false;
  }
  boolean matches=false;
  if (base.getModel() instanceof CustomMatcherModel) {
    if (((CustomMatcherModel)base.getModel()).matches(name,pattern)) {
      matches=true;
    }
  }
 else   if (pattern.isEmpty() || matcher.matches(name)) {
    matches=true;
  }
  return matches;
}
