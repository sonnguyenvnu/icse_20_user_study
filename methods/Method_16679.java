@Override public List<String> parse(DimensionContext context,StrategyConfig config){
  return userService.selectByUserByRole(config.getIdList()).stream().map(UserEntity::getId).collect(Collectors.toList());
}
