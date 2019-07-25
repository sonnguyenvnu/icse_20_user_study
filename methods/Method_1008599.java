/** 
 * This will parse XContent into a  {@link StoredScriptSource}.  The following formats can be parsed: The simple script format with no compiler options or user-defined params: Example: {@code}{"script": "return Math.log(doc.popularity) * 100;"} } The above format requires the lang to be specified using the deprecated stored script namespace (as a url parameter during a put request).  See  {@link ScriptMetaData} for more information aboutthe stored script namespaces. The complex script format using the new stored script namespace where lang and source are required but options is optional: {@code}{ "script" : { "lang" : "<lang>", "source" : "<source>", "options" : { "option0" : "<option0>", "option1" : "<option1>", ... } } } } Example: {@code}{ "script": { "lang" : "painless", "source" : "return Math.log(doc.popularity) * params.multiplier" } } } The use of "source" may also be substituted with "code" for backcompat with 5.3 to 5.5 format. For example: {@code}{ "script" : { "lang" : "<lang>", "code" : "<source>", "options" : { "option0" : "<option0>", "option1" : "<option1>", ... } } } } The simple template format: {@code}{ "query" : ... } } The complex template format: {@code}{ "template": { "query" : ... } } } Note that templates can be handled as both strings and complex JSON objects. Also templates may be part of the 'source' parameter in a script.  The Parser can handle this case as well.
 * @param content The content from the request to be parsed as described above.
 * @return        The parsed {@link StoredScriptSource}.
 */
public static StoredScriptSource parse(BytesReference content,XContentType xContentType){
  try (XContentParser parser=xContentType.xContent().createParser(NamedXContentRegistry.EMPTY,content)){
    Token token=parser.nextToken();
    if (token != Token.START_OBJECT) {
      throw new ParsingException(parser.getTokenLocation(),"unexpected token [" + token + "], expected [{]");
    }
    token=parser.nextToken();
    if (token == Token.END_OBJECT) {
      return new StoredScriptSource(Script.DEFAULT_TEMPLATE_LANG,"",Collections.emptyMap());
    }
    if (token != Token.FIELD_NAME) {
      throw new ParsingException(parser.getTokenLocation(),"unexpected token [" + token + ", expected [" + SCRIPT_PARSE_FIELD.getPreferredName() + ", " + TEMPLATE_PARSE_FIELD.getPreferredName());
    }
    String name=parser.currentName();
    if (SCRIPT_PARSE_FIELD.getPreferredName().equals(name)) {
      token=parser.nextToken();
      if (token == Token.START_OBJECT) {
        return PARSER.apply(parser,null).build();
      }
 else {
        throw new ParsingException(parser.getTokenLocation(),"unexpected token [" + token + "], expected [{, <source>]");
      }
    }
 else {
      if (TEMPLATE_PARSE_FIELD.getPreferredName().equals(name)) {
        token=parser.nextToken();
        if (token == Token.VALUE_STRING) {
          return new StoredScriptSource(Script.DEFAULT_TEMPLATE_LANG,parser.text(),Collections.emptyMap());
        }
      }
      try (XContentBuilder builder=XContentFactory.jsonBuilder()){
        if (token != Token.START_OBJECT) {
          builder.startObject();
          builder.copyCurrentStructure(parser);
          builder.endObject();
        }
 else {
          builder.copyCurrentStructure(parser);
        }
        return new StoredScriptSource(Script.DEFAULT_TEMPLATE_LANG,builder.string(),Collections.emptyMap());
      }
     }
  }
 catch (  IOException ioe) {
    throw new UncheckedIOException(ioe);
  }
}
