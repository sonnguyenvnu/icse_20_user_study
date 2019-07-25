private Criteria from(Part part,Criteria instance,Iterator<?> parameters){
  Part.Type type=part.getType();
  Criteria criteria=instance;
  if (criteria == null) {
    criteria=new Criteria();
  }
switch (type) {
case TRUE:
    return criteria.is(true);
case FALSE:
  return criteria.is(false);
case NEGATING_SIMPLE_PROPERTY:
return criteria.is(parameters.next()).not();
case REGEX:
return criteria.expression(parameters.next().toString());
case LIKE:
case STARTING_WITH:
return criteria.startsWith(parameters.next().toString());
case ENDING_WITH:
return criteria.endsWith(parameters.next().toString());
case CONTAINING:
return criteria.contains(parameters.next().toString());
case GREATER_THAN:
return criteria.greaterThan(parameters.next());
case AFTER:
case GREATER_THAN_EQUAL:
return criteria.greaterThanEqual(parameters.next());
case LESS_THAN:
return criteria.lessThan(parameters.next());
case BEFORE:
case LESS_THAN_EQUAL:
return criteria.lessThanEqual(parameters.next());
case BETWEEN:
return criteria.between(parameters.next(),parameters.next());
case IN:
return criteria.in(asArray(parameters.next()));
case NOT_IN:
return criteria.notIn(asArray(parameters.next()));
case SIMPLE_PROPERTY:
case WITHIN:
{
Object firstParameter=parameters.next();
Object secondParameter=null;
if (type == Part.Type.SIMPLE_PROPERTY) {
if (part.getProperty().getType() != GeoPoint.class) return criteria.is(firstParameter);
 else {
secondParameter=".001km";
}
}
 else {
secondParameter=parameters.next();
}
if (firstParameter instanceof GeoPoint && secondParameter instanceof String) return criteria.within((GeoPoint)firstParameter,(String)secondParameter);
if (firstParameter instanceof Point && secondParameter instanceof Distance) return criteria.within((Point)firstParameter,(Distance)secondParameter);
if (firstParameter instanceof String && secondParameter instanceof String) return criteria.within((String)firstParameter,(String)secondParameter);
}
case NEAR:
{
Object firstParameter=parameters.next();
if (firstParameter instanceof GeoBox) {
return criteria.boundedBy((GeoBox)firstParameter);
}
if (firstParameter instanceof Box) {
return criteria.boundedBy(GeoBox.fromBox((Box)firstParameter));
}
Object secondParameter=parameters.next();
if (firstParameter instanceof GeoPoint && secondParameter instanceof String) return criteria.within((GeoPoint)firstParameter,(String)secondParameter);
if (firstParameter instanceof Point && secondParameter instanceof Distance) return criteria.within((Point)firstParameter,(Distance)secondParameter);
if (firstParameter instanceof String && secondParameter instanceof String) return criteria.within((String)firstParameter,(String)secondParameter);
}
default :
throw new InvalidDataAccessApiUsageException("Illegal criteria found '" + type + "'.");
}
}
