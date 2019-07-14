@Override public boolean supports(KeyInformation information,JanusGraphPredicate janusgraphPredicate){
  final Class<?> dataType=information.getDataType();
  final Mapping mapping=Mapping.getMapping(information);
  if (mapping != Mapping.DEFAULT && !AttributeUtil.isString(dataType) && !(mapping == Mapping.PREFIX_TREE && AttributeUtil.isGeo(dataType)))   return false;
  if (Number.class.isAssignableFrom(dataType)) {
    return janusgraphPredicate instanceof Cmp;
  }
 else   if (dataType == Geoshape.class) {
switch (mapping) {
case DEFAULT:
      return janusgraphPredicate instanceof Geo && janusgraphPredicate != Geo.CONTAINS;
case PREFIX_TREE:
    return janusgraphPredicate instanceof Geo;
}
}
 else if (AttributeUtil.isString(dataType)) {
switch (mapping) {
case DEFAULT:
case TEXT:
  return janusgraphPredicate == Text.CONTAINS || janusgraphPredicate == Text.CONTAINS_PREFIX || janusgraphPredicate == Text.CONTAINS_REGEX || janusgraphPredicate == Text.CONTAINS_FUZZY;
case STRING:
return janusgraphPredicate instanceof Cmp || janusgraphPredicate == Text.REGEX || janusgraphPredicate == Text.PREFIX || janusgraphPredicate == Text.FUZZY;
case TEXTSTRING:
return janusgraphPredicate instanceof Text || janusgraphPredicate instanceof Cmp;
}
}
 else if (dataType == Date.class || dataType == Instant.class) {
return janusgraphPredicate instanceof Cmp;
}
 else if (dataType == Boolean.class) {
return janusgraphPredicate == Cmp.EQUAL || janusgraphPredicate == Cmp.NOT_EQUAL;
}
 else if (dataType == UUID.class) {
return janusgraphPredicate == Cmp.EQUAL || janusgraphPredicate == Cmp.NOT_EQUAL;
}
return false;
}
