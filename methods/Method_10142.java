/** 
 * Adds the specified visit.
 * @param visit the specified visit
 * @return {@code true} if visited before, returns {@code false} otherwise
 */
@Transactional public boolean add(final JSONObject visit){
  try {
    final String url=visit.optString(Visit.VISIT_URL);
    final String ip=visit.optString(Visit.VISIT_IP);
    final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Visit.VISIT_URL,FilterOperator.EQUAL,url),new PropertyFilter(Visit.VISIT_IP,FilterOperator.EQUAL,ip))).setPageCount(1);
    final long count=visitRepository.count(query);
    if (0 < count) {
      return true;
    }
    String ua=visit.optString(Visit.VISIT_UA);
    if (StringUtils.length(ua) > Common.MAX_LENGTH_UA) {
      ua=StringUtils.substring(ua,0,Common.MAX_LENGTH_UA);
    }
    visit.put(Visit.VISIT_UA,ua);
    String referer=visit.optString(Visit.VISIT_REFERER_URL);
    if (StringUtils.length(referer) > Common.MAX_LENGTH_URL) {
      referer=StringUtils.substring(referer,0,Common.MAX_LENGTH_URL);
    }
    visit.put(Visit.VISIT_REFERER_URL,referer);
    visitRepository.add(visit);
    return false;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Adds a visit failed",e);
    return true;
  }
}
