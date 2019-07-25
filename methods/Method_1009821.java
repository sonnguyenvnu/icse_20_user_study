/** 
 * Checks if the current version equals the parsed version.
 * @param version the version to compare to, the left-handoperand of the "equal" operator
 * @return {@code true} if the version equals theparsed version or  {@code false} otherwise
 */
@Override public boolean interpret(Version version){
  return version.equals(mParsedVersion);
}
