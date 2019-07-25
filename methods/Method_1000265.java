public static void register(){
  ServiceGateway.sharedInstance().registerHandler(new NavigationService_pageOnStart());
}
