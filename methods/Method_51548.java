/** 
 * Does the given Rule apply to the given LanguageVersion? If so, the Language must be the same and be between the minimum and maximums versions on the Rule.
 * @param rule The rule.
 * @param languageVersion The language version.
 * @return <code>true</code> if the given rule matches the given language,which means, that the rule would be executed.
 */
public static boolean applies(Rule rule,LanguageVersion languageVersion){
  final LanguageVersion min=rule.getMinimumLanguageVersion();
  final LanguageVersion max=rule.getMaximumLanguageVersion();
  return rule.getLanguage().equals(languageVersion.getLanguage()) && (min == null || min.compareTo(languageVersion) <= 0) && (max == null || max.compareTo(languageVersion) >= 0);
}
