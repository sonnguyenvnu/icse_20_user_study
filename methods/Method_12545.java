private static List<Map<String,Object>> convertLiquibaseChangesets(List<Map<String,Object>> changeSets){
  return changeSets.stream().map(changeset -> {
    Map<String,Object> converted=new LinkedHashMap<>();
    converted.put("id",changeset.get("ID"));
    converted.put("author",changeset.get("AUTHOR"));
    converted.put("changeLog",changeset.get("FILENAME"));
    if (changeset.get("DATEEXECUTED") instanceof Long) {
      converted.put("dateExecuted",new Date((Long)changeset.get("DATEEXECUTED")));
    }
    converted.put("orderExecuted",changeset.get("ORDEREXECUTED"));
    converted.put("execType",changeset.get("EXECTYPE"));
    converted.put("checksum",changeset.get("MD5SUM"));
    converted.put("description",changeset.get("DESCRIPTION"));
    converted.put("comments",changeset.get("COMMENTS"));
    converted.put("tag",changeset.get("TAG"));
    converted.put("contexts",changeset.get("CONTEXTS") instanceof String ? new LinkedHashSet<>(asList(((String)changeset.get("CONTEXTS")).split(",\\s*"))) : emptySet());
    converted.put("labels",changeset.get("LABELS") instanceof String ? new LinkedHashSet<>(asList(((String)changeset.get("LABELS")).split(",\\s*"))) : emptySet());
    converted.put("deploymentId",changeset.get("DEPLOYMENT_ID"));
    return converted;
  }
).collect(toList());
}
