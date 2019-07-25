@Bean public Mars mars(){
  demoService.service();
  return new Mars(1000,"??");
}
