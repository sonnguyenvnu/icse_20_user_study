public boolean equals(final PathInfo pathInfo){
  return null != pathInfo && new EqualsBuilder().append(path,pathInfo.path).append(group,pathInfo.group).append(fileType,pathInfo.fileType).isEquals();
}
