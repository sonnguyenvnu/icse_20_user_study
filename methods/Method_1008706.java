private static ContextMapping load(Map<String,Object> contextConfig,Version indexVersionCreated){
  String name=extractRequiredValue(contextConfig,FIELD_NAME);
  String type=extractRequiredValue(contextConfig,FIELD_TYPE);
  final ContextMapping contextMapping;
switch (Type.fromString(type)) {
case CATEGORY:
    contextMapping=CategoryContextMapping.load(name,contextConfig);
  break;
case GEO:
contextMapping=GeoContextMapping.load(name,contextConfig);
break;
default :
throw new ElasticsearchParseException("unknown context type[" + type + "]");
}
DocumentMapperParser.checkNoRemainingFields(name,contextConfig,indexVersionCreated);
return contextMapping;
}
