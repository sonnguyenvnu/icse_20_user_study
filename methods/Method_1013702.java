@Override boolean stop(){
  return configService.isSentinelAutoProcess();
}
