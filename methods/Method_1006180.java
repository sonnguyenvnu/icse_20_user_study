public static Optional<? extends Identifier> parse(String fieldName,String input){
  if (StringUtil.isBlank(input)) {
    return Optional.empty();
  }
  Function<String,Optional<? extends Identifier>> parser=getParserForField(fieldName);
  return parser.apply(input);
}
