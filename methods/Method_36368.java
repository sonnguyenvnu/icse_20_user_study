@Bean public ModelCreatingStage modelCreatingStage(ApplicationContext applicationContext){
  return new ModelCreatingStage((AbstractApplicationContext)applicationContext);
}
