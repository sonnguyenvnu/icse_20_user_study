@Bean @ConditionalOnMissingBean(LogicPrimaryKeyValidator.class) public LogicPrimaryKeyValidator logicPrimaryKeyValidator(){
  return DefaultLogicPrimaryKeyValidator.getInstrance();
}
