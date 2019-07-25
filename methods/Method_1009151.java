@Override public ValidatorList process(final ProcessingReport report,final ValidatorList input) throws ProcessingException {
  final SchemaContext context=input.getContext();
  final JsonNode node=context.getSchema().getNode().get("format");
  if (node == null)   return input;
  final String fmt=node.textValue();
  final FormatAttribute attr=attributes.get(fmt);
  if (attr == null) {
    report.warn(input.newMessage().put("domain","validation").put("keyword","format").setMessage(bundle.getMessage("warn.format.notSupported")).putArgument("attribute",fmt));
    return input;
  }
  final NodeType type=context.getInstanceType();
  if (!attr.supportedTypes().contains(type))   return input;
  final List<KeywordValidator> validators=Lists.newArrayList(input);
  validators.add(formatValidator(attr));
  return new ValidatorList(context,validators);
}
