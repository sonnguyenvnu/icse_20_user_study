/** 
 * Sets the aliases that will be associated with the index when it gets created
 */
public PutIndexTemplateRequest aliases(BytesReference source){
  try (XContentParser parser=XContentHelper.createParser(NamedXContentRegistry.EMPTY,source)){
    parser.nextToken();
    while ((parser.nextToken()) != XContentParser.Token.END_OBJECT) {
      alias(Alias.fromXContent(parser));
    }
    return this;
  }
 catch (  IOException e) {
    throw new ElasticsearchParseException("Failed to parse aliases",e);
  }
}
