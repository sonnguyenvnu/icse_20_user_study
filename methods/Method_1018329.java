@Override public boolean supports(LookupContext context){
  return mappings.getObject().hasMappingFor(context.getType());
}
