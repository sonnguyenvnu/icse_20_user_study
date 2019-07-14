protected Set<Correlation> buildCorrelations(String correlations){
  if (StringUtils.isEmpty(correlations)) {
    return new LinkedHashSet<>();
  }
  JSONArray correlationsConfig=JSON.parseArray(correlations);
  Set<Correlation> correlations1=new LinkedHashSet<>();
  for (int i=0; i < correlationsConfig.size(); i++) {
    JSONObject single=correlationsConfig.getJSONObject(i);
    String target=single.getString("target");
    String alias=single.getString("alias");
    String condition=single.getString("condition");
    Objects.requireNonNull(target);
    Objects.requireNonNull(condition);
    Correlation correlation=new Correlation(target,alias,condition);
    correlation.setJoin(Correlation.JOIN.valueOf(String.valueOf(single.getOrDefault("join","LEFT")).toUpperCase()));
    JSONObject properties=single.getJSONObject("properties");
    if (properties != null) {
      properties.forEach(correlation::setProperty);
    }
    correlations1.add(correlation);
  }
  return correlations1;
}
