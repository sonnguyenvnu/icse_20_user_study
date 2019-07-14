@Override public void init(String[] args){
  this.arguments=args;
  BizServiceHolder.setBizManagerService(bizManagerService);
  BizServiceHolder.setBizFactoryService(bizFactoryService);
  BizServiceHolder.setArguments(arguments);
}
