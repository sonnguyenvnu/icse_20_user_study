/** 
 * The settings to create the index with (either json/yaml/properties format)
 */
@SuppressWarnings("unchecked") public CreateIndexRequest settings(Map source){
  try {
    XContentBuilder builder=XContentFactory.contentBuilder(XContentType.JSON);
    builder.map(source);
    settings(builder.string(),XContentType.JSON);
  }
 catch (  IOException e) {
    throw new ElasticsearchGenerationException("Failed to generate [" + source + "]",e);
  }
  return this;
}
