/** 
 * Sets the zone provider factory without performing the security check.
 * @param provider  provider to use, or null for default
 * @return the provider
 * @throws IllegalArgumentException if the provider is invalid
 */
private static Provider validateProvider(Provider provider){
  Set<String> ids=provider.getAvailableIDs();
  if (ids == null || ids.size() == 0) {
    throw new IllegalArgumentException("The provider doesn't have any available ids");
  }
  if (!ids.contains("UTC")) {
    throw new IllegalArgumentException("The provider doesn't support UTC");
  }
  if (!UTC.equals(provider.getZone("UTC"))) {
    throw new IllegalArgumentException("Invalid UTC zone provided");
  }
  return provider;
}
