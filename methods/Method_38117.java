/** 
 * Sets properties from the map.
 */
public Q setMap(final Map parameters){
  if (parameters == null) {
    return _this();
  }
  init();
  query.forEachNamedParameter(p -> {
    final String paramName=p.name;
    setObject(paramName,parameters.get(paramName));
  }
);
  return _this();
}
