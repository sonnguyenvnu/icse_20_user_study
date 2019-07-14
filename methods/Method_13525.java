private void callParam(){
  System.out.println(restService.param("mercyblitz"));
  System.out.println(dubboFeignRestService.param("mercyblitz"));
  System.out.println(feignRestService.param("mercyblitz"));
}
