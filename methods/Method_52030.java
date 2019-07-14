/** 
 * Returns the immutable list representation of this package name. The list's tail is shared with all packages that start with the same prefix.
 * @param pack The package declaration, may be null
 */
private ImmutableList<String> getPackageList(ASTPackageDeclaration pack){
  if (pack == null) {
    return ListFactory.emptyList();
  }
  final String image=pack.getPackageNameImage();
  ImmutableList<String> fullExisting=FOUND_PACKAGES.get(image);
  if (fullExisting != null) {
    return fullExisting;
  }
  final String[] allPacks=image.split("\\.");
  ImmutableList<String> longestPrefix=getLongestPackagePrefix(image,allPacks.length);
  StringBuilder prefixImage=new StringBuilder();
  for (  String p : longestPrefix) {
    prefixImage.append(p);
  }
  for (int i=longestPrefix.size(); i < allPacks.length; i++) {
    longestPrefix=longestPrefix.prepend(allPacks[i]);
    prefixImage.append(allPacks[i]);
    FOUND_PACKAGES.put(prefixImage.toString(),longestPrefix);
  }
  return longestPrefix;
}
