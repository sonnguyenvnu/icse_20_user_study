private String getTableLineForOption(ConfigOption o,String prefix){
  final List<String> colData=new ArrayList<>(10);
  String name=prefix + o.getName();
  if (o.isDeprecated()) {
    ConfigOption<?> successor=o.getDeprecationReplacement();
    if (null == successor) {
      name="[deprecation-warning]*Deprecated* [line-through]#" + name + "#";
    }
 else {
      name="[deprecation-warning]*Deprecated.  See " + getFullPath(successor) + "* [line-through]#" + name + "#";
    }
  }
  colData.add(name);
  colData.add(removeDelim(o.getDescription()));
  colData.add(o.getDatatype().getSimpleName());
  colData.add(removeDelim(getStringForDefaultValue(o)));
  if (showMutability)   colData.add(o.getType().toString());
  String line=Joiner.on(DELIM_PADDING + DELIM + DELIM_PADDING).join(colData);
  if (DELIM_AT_LINE_START) {
    line=DELIM + DELIM_PADDING + line;
  }
  if (DELIM_AT_LINE_END) {
    line=line + DELIM_PADDING + DELIM;
  }
  return line;
}
