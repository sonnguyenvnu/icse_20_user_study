/** 
 * Returns path to the  {@link IgnoreLanguage} binary or null if not available.Currently only   {@link GitLanguage} is supported.
 * @param language current language
 * @return path to binary
 */
@Nullable private static String bin(@NotNull IgnoreLanguage language){
  if (GitLanguage.INSTANCE.equals(language) && GIT_ENABLED) {
    final String bin=GitExecutableManager.getInstance().getPathToGit();
    return StringUtil.nullize(bin);
  }
  return null;
}
