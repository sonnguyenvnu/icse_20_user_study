/** 
 * Sets repository-specific restore settings <p> See repository documentation for more information.
 * @param source repository-specific snapshot settings
 * @return this request
 */
public RestoreSnapshotRequest settings(Map<String,Object> source){
  try {
    XContentBuilder builder=XContentFactory.contentBuilder(XContentType.JSON);
    builder.map(source);
    settings(builder.string(),builder.contentType());
  }
 catch (  IOException e) {
    throw new ElasticsearchGenerationException("Failed to generate [" + source + "]",e);
  }
  return this;
}
