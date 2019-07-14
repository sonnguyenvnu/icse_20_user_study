@JsonProperty("projectIDs") protected void loadProjects(List<Long> projectIDs){
  for (  Long id : projectIDs) {
    File projectDir=getProjectDir(id);
    ProjectMetadata metadata=ProjectMetadataUtilities.load(projectDir);
    mergeEmptyUserMetadata(metadata);
    _projectsMetadata.put(id,metadata);
    if (metadata != null && metadata.getTags() != null) {
      for (      String tag : metadata.getTags()) {
        if (_projectsTags.containsKey(tag)) {
          _projectsTags.put(tag,_projectsTags.get(tag) + 1);
        }
 else {
          _projectsTags.put(tag,1);
        }
      }
    }
  }
}
