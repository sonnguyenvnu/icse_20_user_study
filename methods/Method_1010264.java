@Internal @Mutable public void register(@NotNull DataSourceFactoryRule rule){
  myFactoryRules.addFirst(rule);
}
