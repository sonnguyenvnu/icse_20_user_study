@HystrixCommand(fallbackMethod="fallbackSome") public String getSome(){
  return restTemplate.getForObject("http://some/getsome",String.class);
}
