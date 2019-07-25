/** 
 * recommend a set of names according to a given name
 * @param s a possibly partially matching name
 * @return a set of names that match with the given name using the local dictionary of names
 */
@Override public Set<StringBuilder> recommend(final StringBuilder s){
  final Set<StringBuilder> recommendations=new HashSet<StringBuilder>();
  if (s.length() == 0) {
    return recommendations;
  }
  for (  final Locations service : this.services.values()) {
    recommendations.addAll(service.recommend(s));
  }
  return recommendations;
}
