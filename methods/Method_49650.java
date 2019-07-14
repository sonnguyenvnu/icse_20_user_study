@Override public boolean supports(KeyInformation information,JanusGraphPredicate predicate){
  final Class<?> dataType=information.getDataType();
  final Mapping mapping=Mapping.getMapping(information);
  if (mapping != Mapping.DEFAULT && !AttributeUtil.isString(dataType) && !(mapping == Mapping.PREFIX_TREE && AttributeUtil.isGeo(dataType)))   return false;
  if (Number.class.isAssignableFrom(dataType)) {
    return predicate instanceof Cmp;
  }
 else   if (dataType == Geoshape.class) {
switch (mapping) {
case DEFAULT:
      return predicate == Geo.WITHIN || predicate == Geo.INTERSECT;
case PREFIX_TREE:
    return predicate == Geo.INTERSECT || predicate == Geo.WITHIN || predicate == Geo.CONTAINS;
}
}
 else if (AttributeUtil.isString(dataType)) {
switch (mapping) {
case DEFAULT:
case TEXT:
  return predicate == Text.CONTAINS || predicate == Text.CONTAINS_PREFIX || predicate == Text.CONTAINS_REGEX || predicate == Text.CONTAINS_FUZZY;
case STRING:
return predicate instanceof Cmp || predicate == Text.REGEX || predicate == Text.PREFIX || predicate == Text.FUZZY;
}
}
 else if (dataType == Date.class || dataType == Instant.class) {
return predicate instanceof Cmp;
}
 else if (dataType == Boolean.class) {
return predicate == Cmp.EQUAL || predicate == Cmp.NOT_EQUAL;
}
 else if (dataType == UUID.class) {
return predicate == Cmp.EQUAL || predicate == Cmp.NOT_EQUAL;
}
return false;
}
