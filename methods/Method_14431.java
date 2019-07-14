/** 
 * Import an external project that has been received as a .tar file, expanded, and copied into our workspace directory.
 * @param projectID
 */
@Override public boolean loadProjectMetadata(long projectID){
synchronized (this) {
    ProjectMetadata metadata=ProjectMetadataUtilities.load(getProjectDir(projectID));
    if (metadata == null) {
      metadata=ProjectMetadataUtilities.recover(getProjectDir(projectID),projectID);
    }
    if (metadata != null) {
      _projectsMetadata.put(projectID,metadata);
      if (_projectsTags == null) {
        _projectsTags=new HashMap<String,Integer>();
      }
      if (metadata != null && metadata.getTags() != null) {
        for (        String tag : metadata.getTags()) {
          if (_projectsTags.containsKey(tag)) {
            _projectsTags.put(tag,_projectsTags.get(tag) + 1);
          }
 else {
            _projectsTags.put(tag,1);
          }
        }
      }
      return true;
    }
 else {
      return false;
    }
  }
}
