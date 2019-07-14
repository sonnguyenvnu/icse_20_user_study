private boolean isHystrixOnClasspath(){
  try {
    Class.forName("com.netflix.hystrix.HystrixCommand");
    return true;
  }
 catch (  ClassNotFoundException e) {
    return false;
  }
}
