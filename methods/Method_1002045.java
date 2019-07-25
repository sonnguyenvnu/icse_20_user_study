@Override public void initialize(Bootstrap<KeywhizConfig> bootstrap){
  customizeObjectMapper(bootstrap.getObjectMapper());
  logger.debug("Registering commands");
  bootstrap.addCommand(new PreviewMigrateCommand());
  bootstrap.addCommand(new MigrateCommand());
  bootstrap.addCommand(new DbSeedCommand());
  bootstrap.addCommand(new GenerateAesKeyCommand());
  bootstrap.addCommand(new AddUserCommand());
  bootstrap.addCommand(new DropDeletedSecretsCommand());
}
