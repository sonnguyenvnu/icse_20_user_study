public static P createPredicateWithValue(String predicate,Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
  if (value instanceof Collection) {
switch (predicate) {
case "and":
      return new AndP((List<P>)value);
case "or":
    return new OrP((List<P>)value);
case "between":
  return P.between(((List)value).get(0),((List)value).get(1));
case "inside":
return P.inside(((List)value).get(0),((List)value).get(1));
case "outside":
return P.outside(((List)value).get(0),((List)value).get(1));
case "within":
return P.within((Collection)value);
case "without":
return P.without((Collection)value);
default :
return (P)P.class.getMethod(predicate,Collection.class).invoke(null,(Collection)value);
}
}
 else {
switch (predicate) {
case "geoIntersect":
return Geo.geoIntersect(value);
case "geoDisjoint":
return Geo.geoDisjoint(value);
case "geoWithin":
return Geo.geoWithin(value);
case "geoContains":
return Geo.geoContains(value);
case "textContains":
return Text.textContains(value);
case "textContainsFuzzy":
return Text.textContainsFuzzy(value);
case "textContainsPrefix":
return Text.textContainsPrefix(value);
case "textContainsRegex":
return Text.textContainsRegex(value);
case "textFuzzy":
return Text.textFuzzy(value);
case "textPrefix":
return Text.textPrefix(value);
case "textRegex":
return Text.textRegex(value);
default :
return (P)P.class.getMethod(predicate,Object.class).invoke(null,value);
}
}
}
