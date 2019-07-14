/** 
 * Checks that the legal packages are okay.
 * @param legalNamePrefixes Prefixes to check. Can be null, but not contain null
 * @throws IllegalArgumentException If the prefixes contain null
 * @throws IllegalArgumentException If one name that does not look like a package name
 */
private void checkValidPackages(String[] legalNamePrefixes) throws IllegalArgumentException {
  if (legalNamePrefixes == null) {
    return;
  }
  for (  String name : legalNamePrefixes) {
    if (name == null) {
      throw new IllegalArgumentException("Null is not allowed in the legal package names:" + Arrays.toString(legalNamePrefixes));
    }
 else     if (!PACKAGE_NAME_PATTERN.matcher(name).matches()) {
      throw new IllegalArgumentException("One name is not a package: '" + name + "'");
    }
  }
}
