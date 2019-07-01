@Override public List<ArtifactMetadata> _XXXXX_(String key,String text,String repositoryId,boolean e) throws MetadataRepositoryException {
  String theKey=key == null ? "*" : "[" + key + "]";
  String projectVersionCondition=e ? "(projectVersion." + theKey + " = $value)" : "contains([projectVersion]." + theKey + ", $value)";
  String facetCondition=e ? "(facet." + theKey + " = $value)" : "contains([facet]." + theKey + ", $value)";
  String q="SELECT * FROM [" + PROJECT_VERSION_NODE_TYPE + "] AS projectVersion LEFT OUTER JOIN ["+ ARTIFACT_NODE_TYPE+ "] AS artifact ON ISCHILDNODE(artifact, projectVersion) LEFT OUTER JOIN ["+ FACET_NODE_TYPE+ "] AS facet ON ISCHILDNODE(facet, projectVersion) WHERE ("+ projectVersionCondition+ " OR "+ facetCondition+ ")";
  return runJcrQuery(repositoryId,q,ImmutableMap.of("value",text));
}