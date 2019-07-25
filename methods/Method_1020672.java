public static void main(String[] args){
  log.debug("VolePortalDataApplication startup main");
  SpringApplication application=new SpringApplication(VolePortalDataApplication.class);
  application.setBannerMode(Banner.Mode.OFF);
  application.run(args);
}
