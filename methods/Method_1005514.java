@Scheduled(fixedDelay=5000) public void hello(){
  System.out.println("The first message is: " + this.myConfig.getMessage());
  System.out.println("The other message is: " + this.dummyConfig.getMessage());
}
