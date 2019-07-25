/** 
 * This will parse XContent into a  {@link Script}.  The following formats can be parsed: The simple format defaults to an  {@link ScriptType#INLINE} with no compiler options or user-defined params:Example: {@code "return Math.log(doc.popularity) * 100;"}The complex format where  {@link ScriptType} and idOrCode are required while lang, options and params are not required.{@code}{ // Exactly one of "id" or "source" must be specified "id" : "<id>", // OR "source": "<source>", "lang" : "<lang>", "options" : { "option0" : "<option0>", "option1" : "<option1>", ... }, "params" : { "param0" : "<param0>", "param1" : "<param1>", ... } } } Example: {@code}{ "source" : "return Math.log(doc.popularity) * params.multiplier", "lang" : "painless", "params" : { "multiplier" : 100.0 } } } This also handles templates in a special way.  If a complexly formatted query is specified as another complex JSON object the query is assumed to be a template, and the format will be preserved. {@code}{ "source" : { "query" : ... }, "lang" : "<lang>", "options" : { "option0" : "<option0>", "option1" : "<option1>", ... }, "params" : { "param0" : "<param0>", "param1" : "<param1>", ... } } }
 * @param parser       The {@link XContentParser} to be used.
 * @param defaultLang  The default language to use if no language is specified.  The default language isn't necessarilythe one defined by  {@link Script#DEFAULT_SCRIPT_LANG} due to backwards compatibility requirementsrelated to stored queries using previously default languages.
 * @return             The parsed {@link Script}.
 */
public static Script parse(XContentParser parser,String defaultLang) throws IOException {
  Objects.requireNonNull(defaultLang);
  Token token=parser.currentToken();
  if (token == null) {
    token=parser.nextToken();
  }
  if (token == Token.VALUE_STRING) {
    return new Script(ScriptType.INLINE,defaultLang,parser.text(),Collections.emptyMap());
  }
  return PARSER.apply(parser,null).build(defaultLang);
}
