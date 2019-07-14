public Map<String,Object> getFilter(Condition<?> condition,KeyInformation.StoreRetriever information){
  if (condition instanceof PredicateCondition) {
    final PredicateCondition<String,?> atom=(PredicateCondition)condition;
    Object value=atom.getValue();
    final String key=atom.getKey();
    final JanusGraphPredicate predicate=atom.getPredicate();
    if (value instanceof Number) {
      Preconditions.checkArgument(predicate instanceof Cmp,"Relation not supported on numeric types: " + predicate);
      final Cmp numRel=(Cmp)predicate;
switch (numRel) {
case EQUAL:
        return compat.term(key,value);
case NOT_EQUAL:
      return compat.boolMustNot(compat.term(key,value));
case LESS_THAN:
    return compat.lt(key,value);
case LESS_THAN_EQUAL:
  return compat.lte(key,value);
case GREATER_THAN:
return compat.gt(key,value);
case GREATER_THAN_EQUAL:
return compat.gte(key,value);
default :
throw new IllegalArgumentException("Unexpected relation: " + numRel);
}
}
 else if (value instanceof String) {
final Mapping mapping=getStringMapping(information.get(key));
final String fieldName;
if (mapping == Mapping.TEXT && !(Text.HAS_CONTAINS.contains(predicate) || predicate instanceof Cmp)) throw new IllegalArgumentException("Text mapped string values only support CONTAINS and Compare queries and not: " + predicate);
if (mapping == Mapping.STRING && Text.HAS_CONTAINS.contains(predicate)) throw new IllegalArgumentException("String mapped string values do not support CONTAINS queries: " + predicate);
if (mapping == Mapping.TEXTSTRING && !(Text.HAS_CONTAINS.contains(predicate) || (predicate instanceof Cmp && predicate != Cmp.EQUAL))) {
fieldName=getDualMappingName(key);
}
 else {
fieldName=key;
}
if (predicate == Text.CONTAINS || predicate == Cmp.EQUAL) {
return compat.match(fieldName,value);
}
 else if (predicate == Text.CONTAINS_PREFIX) {
if (!ParameterType.TEXT_ANALYZER.hasParameter(information.get(key).getParameters())) value=((String)value).toLowerCase();
return compat.prefix(fieldName,value);
}
 else if (predicate == Text.CONTAINS_REGEX) {
if (!ParameterType.TEXT_ANALYZER.hasParameter(information.get(key).getParameters())) value=((String)value).toLowerCase();
return compat.regexp(fieldName,value);
}
 else if (predicate == Text.PREFIX) {
return compat.prefix(fieldName,value);
}
 else if (predicate == Text.REGEX) {
return compat.regexp(fieldName,value);
}
 else if (predicate == Cmp.NOT_EQUAL) {
return compat.boolMustNot(compat.match(fieldName,value));
}
 else if (predicate == Text.FUZZY || predicate == Text.CONTAINS_FUZZY) {
return compat.fuzzyMatch(fieldName,value);
}
 else if (predicate == Cmp.LESS_THAN) {
return compat.lt(fieldName,value);
}
 else if (predicate == Cmp.LESS_THAN_EQUAL) {
return compat.lte(fieldName,value);
}
 else if (predicate == Cmp.GREATER_THAN) {
return compat.gt(fieldName,value);
}
 else if (predicate == Cmp.GREATER_THAN_EQUAL) {
return compat.gte(fieldName,value);
}
 else throw new IllegalArgumentException("Predicate is not supported for string value: " + predicate);
}
 else if (value instanceof Geoshape && Mapping.getMapping(information.get(key)) == Mapping.DEFAULT) {
final Geoshape shape=(Geoshape)value;
Preconditions.checkArgument(predicate instanceof Geo && predicate != Geo.CONTAINS,"Relation not supported on geopoint types: " + predicate);
final Map<String,Object> query;
switch (shape.getType()) {
case CIRCLE:
final Geoshape.Point center=shape.getPoint();
query=compat.geoDistance(key,center.getLatitude(),center.getLongitude(),shape.getRadius());
break;
case BOX:
final Geoshape.Point southwest=shape.getPoint(0);
final Geoshape.Point northeast=shape.getPoint(1);
query=compat.geoBoundingBox(key,southwest.getLatitude(),southwest.getLongitude(),northeast.getLatitude(),northeast.getLongitude());
break;
case POLYGON:
final List<List<Double>> points=IntStream.range(0,shape.size()).mapToObj(i -> ImmutableList.of(shape.getPoint(i).getLongitude(),shape.getPoint(i).getLatitude())).collect(Collectors.toList());
query=compat.geoPolygon(key,points);
break;
default :
throw new IllegalArgumentException("Unsupported or invalid search shape type for geopoint: " + shape.getType());
}
return predicate == Geo.DISJOINT ? compat.boolMustNot(query) : query;
}
 else if (value instanceof Geoshape) {
Preconditions.checkArgument(predicate instanceof Geo,"Relation not supported on geoshape types: " + predicate);
final Geoshape shape=(Geoshape)value;
final Map<String,Object> geo;
switch (shape.getType()) {
case CIRCLE:
final Geoshape.Point center=shape.getPoint();
geo=ImmutableMap.of(ES_TYPE_KEY,"circle",ES_GEO_COORDS_KEY,ImmutableList.of(center.getLongitude(),center.getLatitude()),"radius",shape.getRadius() + "km");
break;
case BOX:
final Geoshape.Point southwest=shape.getPoint(0);
final Geoshape.Point northeast=shape.getPoint(1);
geo=ImmutableMap.of(ES_TYPE_KEY,"envelope",ES_GEO_COORDS_KEY,ImmutableList.of(ImmutableList.of(southwest.getLongitude(),northeast.getLatitude()),ImmutableList.of(northeast.getLongitude(),southwest.getLatitude())));
break;
case LINE:
final List lineCoords=IntStream.range(0,shape.size()).mapToObj(i -> ImmutableList.of(shape.getPoint(i).getLongitude(),shape.getPoint(i).getLatitude())).collect(Collectors.toList());
geo=ImmutableMap.of(ES_TYPE_KEY,"linestring",ES_GEO_COORDS_KEY,lineCoords);
break;
case POLYGON:
final List polyCoords=IntStream.range(0,shape.size()).mapToObj(i -> ImmutableList.of(shape.getPoint(i).getLongitude(),shape.getPoint(i).getLatitude())).collect(Collectors.toList());
geo=ImmutableMap.of(ES_TYPE_KEY,"polygon",ES_GEO_COORDS_KEY,ImmutableList.of(polyCoords));
break;
case POINT:
geo=ImmutableMap.of(ES_TYPE_KEY,"point",ES_GEO_COORDS_KEY,ImmutableList.of(shape.getPoint().getLongitude(),shape.getPoint().getLatitude()));
break;
default :
throw new IllegalArgumentException("Unsupported or invalid search shape type: " + shape.getType());
}
return compat.geoShape(key,geo,(Geo)predicate);
}
 else if (value instanceof Date || value instanceof Instant) {
Preconditions.checkArgument(predicate instanceof Cmp,"Relation not supported on date types: " + predicate);
final Cmp numRel=(Cmp)predicate;
if (value instanceof Instant) {
value=Date.from((Instant)value);
}
switch (numRel) {
case EQUAL:
return compat.term(key,value);
case NOT_EQUAL:
return compat.boolMustNot(compat.term(key,value));
case LESS_THAN:
return compat.lt(key,value);
case LESS_THAN_EQUAL:
return compat.lte(key,value);
case GREATER_THAN:
return compat.gt(key,value);
case GREATER_THAN_EQUAL:
return compat.gte(key,value);
default :
throw new IllegalArgumentException("Unexpected relation: " + numRel);
}
}
 else if (value instanceof Boolean) {
final Cmp numRel=(Cmp)predicate;
switch (numRel) {
case EQUAL:
return compat.term(key,value);
case NOT_EQUAL:
return compat.boolMustNot(compat.term(key,value));
default :
throw new IllegalArgumentException("Boolean types only support EQUAL or NOT_EQUAL");
}
}
 else if (value instanceof UUID) {
if (predicate == Cmp.EQUAL) {
return compat.term(key,value);
}
 else if (predicate == Cmp.NOT_EQUAL) {
return compat.boolMustNot(compat.term(key,value));
}
 else {
throw new IllegalArgumentException("Only equal or not equal is supported for UUIDs: " + predicate);
}
}
 else throw new IllegalArgumentException("Unsupported type: " + value);
}
 else if (condition instanceof Not) {
return compat.boolMustNot(getFilter(((Not)condition).getChild(),information));
}
 else if (condition instanceof And) {
final List queries=StreamSupport.stream(condition.getChildren().spliterator(),false).map(c -> getFilter(c,information)).collect(Collectors.toList());
return compat.boolMust(queries);
}
 else if (condition instanceof Or) {
final List queries=StreamSupport.stream(condition.getChildren().spliterator(),false).map(c -> getFilter(c,information)).collect(Collectors.toList());
return compat.boolShould(queries);
}
 else throw new IllegalArgumentException("Invalid condition: " + condition);
}
