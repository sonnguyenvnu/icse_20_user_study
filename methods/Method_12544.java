@SuppressWarnings("unchecked") private static Map<String,Object> convertLiquibase(List<Map<String,Object>> reports){
  Map<String,Object> liquibaseBeans=reports.stream().sequential().collect(toMap(r -> (String)r.get("name"),r -> singletonMap("changeSets",LegacyEndpointConverters.convertLiquibaseChangesets((List<Map<String,Object>>)r.get("changeLogs")))));
  return singletonMap("contexts",singletonMap("application",singletonMap("liquibaseBeans",liquibaseBeans)));
}
