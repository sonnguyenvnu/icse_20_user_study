public String buildQueryFilter(Condition<JanusGraphElement> condition,KeyInformation.StoreRetriever information){
  if (condition instanceof PredicateCondition) {
    final PredicateCondition<String,JanusGraphElement> atom=(PredicateCondition<String,JanusGraphElement>)condition;
    final Object value=atom.getValue();
    final String key=atom.getKey();
    final JanusGraphPredicate predicate=atom.getPredicate();
    if (value instanceof Number) {
      final String queryValue=escapeValue(value);
      Preconditions.checkArgument(predicate instanceof Cmp,"Relation not supported on numeric types: " + predicate);
      final Cmp numRel=(Cmp)predicate;
switch (numRel) {
case EQUAL:
        return (key + ":" + queryValue);
case NOT_EQUAL:
      return ("-" + key + ":" + queryValue);
case LESS_THAN:
    return (key + ":[* TO " + queryValue + "}");
case LESS_THAN_EQUAL:
  return (key + ":[* TO " + queryValue + "]");
case GREATER_THAN:
return (key + ":{" + queryValue + " TO *]");
case GREATER_THAN_EQUAL:
return (key + ":[" + queryValue + " TO *]");
default :
throw new IllegalArgumentException("Unexpected relation: " + numRel);
}
}
 else if (value instanceof String) {
final Mapping map=getStringMapping(information.get(key));
assert map == Mapping.TEXT || map == Mapping.STRING;
if (map == Mapping.TEXT && !(Text.HAS_CONTAINS.contains(predicate) || predicate instanceof Cmp)) throw new IllegalArgumentException("Text mapped string values only support CONTAINS and Compare queries and not: " + predicate);
if (map == Mapping.STRING && Text.HAS_CONTAINS.contains(predicate)) throw new IllegalArgumentException("String mapped string values do not support CONTAINS queries: " + predicate);
if (predicate == Text.CONTAINS) {
return tokenize(information,value,key,predicate,ParameterType.TEXT_ANALYZER.findParameter(information.get(key).getParameters(),null));
}
 else if (predicate == Text.PREFIX || predicate == Text.CONTAINS_PREFIX) {
return (key + ":" + escapeValue(value) + "*");
}
 else if (predicate == Text.REGEX || predicate == Text.CONTAINS_REGEX) {
return (key + ":/" + value + "/");
}
 else if (predicate == Cmp.EQUAL) {
final String tokenizer=ParameterType.STRING_ANALYZER.findParameter(information.get(key).getParameters(),null);
if (tokenizer != null) {
return tokenize(information,value,key,predicate,tokenizer);
}
 else {
return (key + ":\"" + escapeValue(value) + "\"");
}
}
 else if (predicate == Cmp.NOT_EQUAL) {
return ("-" + key + ":\"" + escapeValue(value) + "\"");
}
 else if (predicate == Text.FUZZY || predicate == Text.CONTAINS_FUZZY) {
return (key + ":" + escapeValue(value) + "~");
}
 else if (predicate == Cmp.LESS_THAN) {
return (key + ":[* TO \"" + escapeValue(value) + "\"}");
}
 else if (predicate == Cmp.LESS_THAN_EQUAL) {
return (key + ":[* TO \"" + escapeValue(value) + "\"]");
}
 else if (predicate == Cmp.GREATER_THAN) {
return (key + ":{\"" + escapeValue(value) + "\" TO *]");
}
 else if (predicate == Cmp.GREATER_THAN_EQUAL) {
return (key + ":[\"" + escapeValue(value) + "\" TO *]");
}
 else {
throw new IllegalArgumentException("Relation is not supported for string value: " + predicate);
}
}
 else if (value instanceof Geoshape) {
final Mapping map=Mapping.getMapping(information.get(key));
Preconditions.checkArgument(predicate instanceof Geo && predicate != Geo.DISJOINT,"Relation not supported on geo types: " + predicate);
Preconditions.checkArgument(map == Mapping.PREFIX_TREE || predicate == Geo.WITHIN || predicate == Geo.INTERSECT,"Relation not supported on geopoint types: " + predicate);
final Geoshape geo=(Geoshape)value;
if (geo.getType() == Geoshape.Type.CIRCLE && (predicate == Geo.INTERSECT || map == Mapping.DEFAULT)) {
final Geoshape.Point center=geo.getPoint();
return ("{!geofilt sfield=" + key + " pt=" + center.getLatitude() + "," + center.getLongitude() + " d=" + geo.getRadius() + "} distErrPct=0");
}
 else if (geo.getType() == Geoshape.Type.BOX && (predicate == Geo.INTERSECT || map == Mapping.DEFAULT)) {
final Geoshape.Point southwest=geo.getPoint(0);
final Geoshape.Point northeast=geo.getPoint(1);
return (key + ":[" + southwest.getLatitude() + "," + southwest.getLongitude() + " TO " + northeast.getLatitude() + "," + northeast.getLongitude() + "]");
}
 else if (map == Mapping.PREFIX_TREE) {
return key + ":\"" + SPATIAL_PREDICATES.get(predicate) + "(" + geo + ")\" distErrPct=0";
}
 else {
throw new IllegalArgumentException("Unsupported or invalid search shape type: " + geo.getType());
}
}
 else if (value instanceof Date || value instanceof Instant) {
final String s=value.toString();
final String queryValue=escapeValue(value instanceof Date ? toIsoDate((Date)value) : value.toString());
Preconditions.checkArgument(predicate instanceof Cmp,"Relation not supported on date types: " + predicate);
final Cmp numRel=(Cmp)predicate;
switch (numRel) {
case EQUAL:
return (key + ":" + queryValue);
case NOT_EQUAL:
return ("-" + key + ":" + queryValue);
case LESS_THAN:
return (key + ":[* TO " + queryValue + "}");
case LESS_THAN_EQUAL:
return (key + ":[* TO " + queryValue + "]");
case GREATER_THAN:
return (key + ":{" + queryValue + " TO *]");
case GREATER_THAN_EQUAL:
return (key + ":[" + queryValue + " TO *]");
default :
throw new IllegalArgumentException("Unexpected relation: " + numRel);
}
}
 else if (value instanceof Boolean) {
final Cmp numRel=(Cmp)predicate;
final String queryValue=escapeValue(value);
switch (numRel) {
case EQUAL:
return (key + ":" + queryValue);
case NOT_EQUAL:
return ("-" + key + ":" + queryValue);
default :
throw new IllegalArgumentException("Boolean types only support EQUAL or NOT_EQUAL");
}
}
 else if (value instanceof UUID) {
if (predicate == Cmp.EQUAL) {
return (key + ":\"" + escapeValue(value) + "\"");
}
 else if (predicate == Cmp.NOT_EQUAL) {
return ("-" + key + ":\"" + escapeValue(value) + "\"");
}
 else {
throw new IllegalArgumentException("Relation is not supported for uuid value: " + predicate);
}
}
 else throw new IllegalArgumentException("Unsupported type: " + value);
}
 else if (condition instanceof Not) {
final String sub=buildQueryFilter(((Not)condition).getChild(),information);
if (StringUtils.isNotBlank(sub)) return "-(" + sub + ")";
 else return "";
}
 else if (condition instanceof And) {
final int numChildren=((And)condition).size();
final StringBuilder sb=new StringBuilder();
for (final Condition<JanusGraphElement> c : condition.getChildren()) {
final String sub=buildQueryFilter(c,information);
if (StringUtils.isBlank(sub)) continue;
if (!sub.startsWith("-") && numChildren > 1) sb.append("+");
sb.append(sub).append(" ");
}
return sb.toString();
}
 else if (condition instanceof Or) {
final StringBuilder sb=new StringBuilder();
int element=0;
for (final Condition<JanusGraphElement> c : condition.getChildren()) {
final String sub=buildQueryFilter(c,information);
if (StringUtils.isBlank(sub)) continue;
if (element == 0) sb.append("(");
 else sb.append(" OR ");
sb.append(sub);
element++;
}
if (element > 0) sb.append(")");
return sb.toString();
}
 else {
throw new IllegalArgumentException("Invalid condition: " + condition);
}
}
