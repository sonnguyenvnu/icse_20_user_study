/** 
 * Checks if the current version is greater than or equal to the parsed version.
 * @param version the version to compare to, the left-hand operandof the "greater than or equal to" operator
 * @return {@code true} if the version is greater than or equalto the parsed version or  {@code false} otherwise
 */
@Override public boolean interpret(Version version){
  return version.compareTo(mParsedVersion) >= 0;
}
