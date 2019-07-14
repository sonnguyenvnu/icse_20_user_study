private ToXContent make(Condition cond,String name,Object value) throws SqlParseException {
  ToXContent x=null;
switch (cond.getOpear()) {
case ISN:
case IS:
case N:
case EQ:
    if (value == null || value instanceof SQLIdentifierExpr) {
      if (value == null || ((SQLIdentifierExpr)value).getName().equalsIgnoreCase("missing")) {
        x=QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(name));
      }
 else {
        throw new SqlParseException(String.format("Cannot recoginze Sql identifer %s",((SQLIdentifierExpr)value).getName()));
      }
      break;
    }
 else {
      x=QueryBuilders.matchPhraseQuery(name,value);
      break;
    }
case LIKE:
case NLIKE:
  String queryStr=((String)value);
queryStr=queryStr.replace('%','*').replace('_','?');
queryStr=queryStr.replace("&PERCENT","%").replace("&UNDERSCORE","_");
x=QueryBuilders.wildcardQuery(name,queryStr);
break;
case REGEXP:
case NREGEXP:
Object[] values=(Object[])value;
RegexpQueryBuilder regexpQuery=QueryBuilders.regexpQuery(name,values[0].toString());
if (1 < values.length) {
String[] flags=values[1].toString().split("\\|");
RegexpFlag[] regexpFlags=new RegexpFlag[flags.length];
for (int i=0; i < flags.length; ++i) {
regexpFlags[i]=RegexpFlag.valueOf(flags[i]);
}
regexpQuery.flags(regexpFlags);
}
if (2 < values.length) {
regexpQuery.maxDeterminizedStates(Integer.parseInt(values[2].toString()));
}
x=regexpQuery;
break;
case GT:
x=QueryBuilders.rangeQuery(name).gt(value);
break;
case GTE:
x=QueryBuilders.rangeQuery(name).gte(value);
break;
case LT:
x=QueryBuilders.rangeQuery(name).lt(value);
break;
case LTE:
x=QueryBuilders.rangeQuery(name).lte(value);
break;
case NIN:
case IN:
if (cond.getNameExpr() instanceof SQLCaseExpr) {
String scriptCode=new CaseWhenParser((SQLCaseExpr)cond.getNameExpr(),null,null).parseCaseWhenInWhere((Object[])value);
x=QueryBuilders.scriptQuery(new Script(scriptCode));
}
 else {
values=(Object[])value;
MatchPhraseQueryBuilder[] matchQueries=new MatchPhraseQueryBuilder[values.length];
for (int i=0; i < values.length; i++) {
matchQueries[i]=QueryBuilders.matchPhraseQuery(name,values[i]);
}
BoolQueryBuilder boolQuery=QueryBuilders.boolQuery();
for (MatchPhraseQueryBuilder matchQuery : matchQueries) {
boolQuery.should(matchQuery);
}
x=boolQuery;
}
break;
case BETWEEN:
case NBETWEEN:
x=QueryBuilders.rangeQuery(name).gte(((Object[])value)[0]).lte(((Object[])value)[1]);
break;
case GEO_INTERSECTS:
String wkt=cond.getValue().toString();
try {
ShapeBuilder shapeBuilder=getShapeBuilderFromString(wkt);
x=QueryBuilders.geoShapeQuery(cond.getName(),shapeBuilder);
}
 catch (IOException e) {
e.printStackTrace();
throw new SqlParseException("couldn't create shapeBuilder from wkt: " + wkt);
}
break;
case GEO_BOUNDING_BOX:
BoundingBoxFilterParams boxFilterParams=(BoundingBoxFilterParams)cond.getValue();
Point topLeft=boxFilterParams.getTopLeft();
Point bottomRight=boxFilterParams.getBottomRight();
x=QueryBuilders.geoBoundingBoxQuery(cond.getName()).setCorners(topLeft.getLat(),topLeft.getLon(),bottomRight.getLat(),bottomRight.getLon());
break;
case GEO_DISTANCE:
DistanceFilterParams distanceFilterParams=(DistanceFilterParams)cond.getValue();
Point fromPoint=distanceFilterParams.getFrom();
String distance=trimApostrophes(distanceFilterParams.getDistance());
x=QueryBuilders.geoDistanceQuery(cond.getName()).distance(distance).point(fromPoint.getLat(),fromPoint.getLon());
break;
case GEO_POLYGON:
PolygonFilterParams polygonFilterParams=(PolygonFilterParams)cond.getValue();
ArrayList<GeoPoint> geoPoints=new ArrayList<GeoPoint>();
for (Point p : polygonFilterParams.getPolygon()) geoPoints.add(new GeoPoint(p.getLat(),p.getLon()));
GeoPolygonQueryBuilder polygonFilterBuilder=QueryBuilders.geoPolygonQuery(cond.getName(),geoPoints);
x=polygonFilterBuilder;
break;
case NIN_TERMS:
case IN_TERMS:
Object[] termValues=(Object[])value;
if (termValues.length == 1 && termValues[0] instanceof SubQueryExpression) termValues=((SubQueryExpression)termValues[0]).getValues();
Object[] termValuesObjects=new Object[termValues.length];
for (int i=0; i < termValues.length; i++) {
termValuesObjects[i]=parseTermValue(termValues[i]);
}
x=QueryBuilders.termsQuery(name,termValuesObjects);
break;
case NTERM:
case TERM:
Object term=((Object[])value)[0];
x=QueryBuilders.termQuery(name,parseTermValue(term));
break;
case IDS_QUERY:
Object[] idsParameters=(Object[])value;
String[] ids;
String type=idsParameters[0].toString();
if (idsParameters.length == 2 && idsParameters[1] instanceof SubQueryExpression) {
Object[] idsFromSubQuery=((SubQueryExpression)idsParameters[1]).getValues();
ids=arrayOfObjectsToStringArray(idsFromSubQuery,0,idsFromSubQuery.length - 1);
}
 else {
ids=arrayOfObjectsToStringArray(idsParameters,1,idsParameters.length - 1);
}
x=QueryBuilders.idsQuery(type).addIds(ids);
break;
case NNESTED_COMPLEX:
case NESTED_COMPLEX:
if (value == null || !(value instanceof Where)) throw new SqlParseException("unsupported nested condition");
Where whereNested=(Where)value;
BoolQueryBuilder nestedFilter=QueryMaker.explan(whereNested);
x=QueryBuilders.nestedQuery(name,nestedFilter,ScoreMode.None);
break;
case CHILDREN_COMPLEX:
if (value == null || !(value instanceof Where)) throw new SqlParseException("unsupported nested condition");
Where whereChildren=(Where)value;
BoolQueryBuilder childrenFilter=QueryMaker.explan(whereChildren);
x=JoinQueryBuilders.hasChildQuery(name,childrenFilter,ScoreMode.None);
break;
case SCRIPT:
ScriptFilter scriptFilter=(ScriptFilter)value;
Map<String,Object> params=new HashMap<>();
if (scriptFilter.containsParameters()) {
params=scriptFilter.getArgs();
}
x=QueryBuilders.scriptQuery(new Script(scriptFilter.getScriptType(),Script.DEFAULT_SCRIPT_LANG,scriptFilter.getScript(),params));
break;
default :
throw new SqlParseException("not define type " + cond.getName());
}
x=fixNot(cond,x);
return x;
}
