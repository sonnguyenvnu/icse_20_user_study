@Override public void config(){
  addInterceptor(new BiInterceptor());
  add("/bi",BiController.class);
  add("/biCustomer",BiCustomerController.class);
  add("/biRanking",BiRankingController.class);
  add("/biFunnel",BiFunnelController.class);
  add("/biEmployee",BiEmployeeController.class);
}
