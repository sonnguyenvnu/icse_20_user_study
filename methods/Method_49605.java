@Override public boolean supports(KeyInformation information,JanusGraphPredicate janusgraphPredicate){
  if (information.getCardinality() != Cardinality.SINGLE)   return false;
  final Class<?> dataType=information.getDataType();
  final Mapping mapping=Mapping.getMapping(information);
  if (mapping != Mapping.DEFAULT && !AttributeUtil.isString(dataType) && !(mapping == Mapping.PREFIX_TREE && AttributeUtil.isGeo(dataType)))   return false;
  if (Number.class.isAssignableFrom(dataType)) {
    return janusgraphPredicate instanceof Cmp;
  }
 else   if (dataType == Geoshape.class) {
    return janusgraphPredicate == Geo.INTERSECT || janusgraphPredicate == Geo.WITHIN || janusgraphPredicate == Geo.CONTAINS;
  }
 else   if (AttributeUtil.isString(dataType)) {
switch (mapping) {
case DEFAULT:
case TEXT:
      return janusgraphPredicate == Text.CONTAINS || janusgraphPredicate == Text.CONTAINS_PREFIX || janusgraphPredicate == Text.CONTAINS_FUZZY;
case STRING:
    return janusgraphPredicate instanceof Cmp || janusgraphPredicate == Text.PREFIX || janusgraphPredicate == Text.REGEX || janusgraphPredicate == Text.FUZZY;
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
