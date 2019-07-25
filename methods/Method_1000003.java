@Override @Autowired public void init(Args args){
  blockStoreDb=dbManager.getBlockStore();
  services=new ServiceContainer();
}
