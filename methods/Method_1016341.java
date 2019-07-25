/** 
 * recommend a set of names according to a given name
 * @param s a possibly partially matching name
 * @return a set of names that match with the given name using the local dictionary of names
 */
@Override public Set<String> recommend(final String s){
  final Set<String> recommendations=new HashSet<String>();
  if (s.isEmpty()) {
    return recommendations;
  }
  for (  final Locations service : this.services.values()) {
    recommendations.addAll(service.recommend(s));
  }
  return recommendations;
}
