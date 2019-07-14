@Bean public FacebookController facebookController(@Qualifier("facebookRestTemplate") RestOperations facebookRestTemplate){
  FacebookController controller=new FacebookController();
  controller.setFacebookRestTemplate(facebookRestTemplate);
  return controller;
}
