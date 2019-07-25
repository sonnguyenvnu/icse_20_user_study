/** 
 * Parse the script configured in the given settings.
 */
public static Script parse(Settings settings){
  try (XContentBuilder builder=JsonXContent.contentBuilder()){
    builder.startObject();
    settings.toXContent(builder,ToXContent.EMPTY_PARAMS);
    builder.endObject();
    return parse(JsonXContent.jsonXContent.createParser(NamedXContentRegistry.EMPTY,builder.bytes()));
  }
 catch (  IOException e) {
    throw new IllegalStateException(e);
  }
}
