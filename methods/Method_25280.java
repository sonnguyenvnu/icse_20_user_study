/** 
 * see  {@link javax.tools.OptionChecker#isSupportedOption(String)} 
 */
public static int isSupportedOption(String option){
  boolean isSupported=option.startsWith(SEVERITY_PREFIX) || option.startsWith(ErrorProneFlags.PREFIX) || option.startsWith(PATCH_OUTPUT_LOCATION) || option.startsWith(PATCH_CHECKS_PREFIX) || option.startsWith(EXCLUDED_PATHS_PREFIX) || option.equals(IGNORE_UNKNOWN_CHECKS_FLAG) || option.equals(DISABLE_WARNINGS_IN_GENERATED_CODE_FLAG) || option.equals(ERRORS_AS_WARNINGS_FLAG) || option.equals(ENABLE_ALL_CHECKS) || option.equals(DISABLE_ALL_CHECKS) || option.equals(IGNORE_SUPPRESSION_ANNOTATIONS) || option.equals(COMPILING_TEST_ONLY_CODE);
  return isSupported ? 0 : -1;
}
