/** 
 * Sets the repository settings.
 * @param source repository settings
 * @return this request
 */
public PutRepositoryRequest settings(Map<String,Object> source){
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
