private static Option createOneArgOption(final String longOpt,final String description,final boolean required){
  final Option o=new Option(NULL_SHORT_OPT,longOpt,HAS_ARG,description);
  o.setRequired(required);
  o.setArgs(1);
  return o;
}
