@Override public List<String> parse(DimensionContext context,StrategyConfig config){
  String type=config.getConfig("tree").map(String::valueOf).map("-"::concat).orElse("");
  return userService.select(empty().noPaging().where("id","user-in-department" + type,config.getIdList())).stream().map(UserEntity::getId).collect(Collectors.toList());
}
