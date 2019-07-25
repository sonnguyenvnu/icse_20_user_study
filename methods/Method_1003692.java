private DefaultServerConfigBuilder copy(){
  return new DefaultServerConfigBuilder(configDataBuilder,required,baseDirSupplier,serverEnvironment,impositions);
}
