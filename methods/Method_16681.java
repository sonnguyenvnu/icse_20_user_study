@Override @SneakyThrows @SuppressWarnings("all") public List<String> parse(DimensionContext context,StrategyConfig config){
  String expression=config.getStringConfig("expression").orElse(null);
  String expressionLanguage=config.getStringConfig("expressionLanguage").orElse(null);
  if (StringUtils.isEmpty(expression)) {
    return new java.util.ArrayList<>();
  }
  String creatorId=context.getCreatorId();
  Supplier<PersonRelations> creator=() -> relationsManager.getPersonRelationsByUserId(creatorId);
  DynamicScriptEngine engine=DynamicScriptEngineFactory.getEngine(expressionLanguage);
  if (engine == null) {
    throw new UnsupportedOperationException("??????:" + expressionLanguage);
  }
  String id=DigestUtils.md5DigestAsHex(expression.getBytes());
  if (!engine.compiled(id)) {
    engine.compile(id,expression);
  }
  Object obj=engine.execute(id,Maps.<String,Object>buildMap().put("user",creator).put("creator",creator).put("creatorId",creatorId).put("context",context).get()).getIfSuccess();
  Function<Object,String> userIdConverter=o -> {
    if (o instanceof String) {
      return (String)o;
    }
 else     if (o instanceof Relation) {
      Object target=((Relation)o).getTargetObject();
      if (target instanceof PersonEntity) {
        return ((PersonEntity)target).getUserId();
      }
 else       if (target instanceof UserEntity) {
        return ((UserEntity)target).getId();
      }
 else {
        return ((Relation)o).getTarget();
      }
    }
 else {
      log.warn("??????:{}",o);
      return null;
    }
  }
;
  if (obj instanceof List) {
    List<Object> list=((List)obj);
    return list.stream().map(userIdConverter).filter(Objects::nonNull).collect(Collectors.toList());
  }
 else {
    String result=userIdConverter.apply(obj);
    if (result == null) {
      return new java.util.ArrayList<>();
    }
    return Lists.buildList(result).get();
  }
}
