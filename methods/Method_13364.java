/** 
 * Get the subscribed services as a  {@link Set} with configuration order.
 * @return non-null Read-only {@link Set}
 */
public Set<String> subscribedServices(){
  String[] services=commaDelimitedListToStringArray(getSubscribedServices());
  if (services.length < 1) {
    return Collections.emptySet();
  }
  Set<String> subscribedServices=new LinkedHashSet<>();
  for (  String service : services) {
    if (hasText(service)) {
      subscribedServices.add(trimAllWhitespace(service));
    }
  }
  return Collections.unmodifiableSet(subscribedServices);
}
