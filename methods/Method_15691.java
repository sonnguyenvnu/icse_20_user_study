@Autowired(required=false) public void setThirdPartAuthenticationManager(List<ThirdPartAuthenticationManager> thirdPartAuthenticationManager){
  for (  ThirdPartAuthenticationManager manager : thirdPartAuthenticationManager) {
    this.thirdPartAuthenticationManager.put(manager.getTokenType(),manager);
  }
}
