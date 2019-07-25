@Override public void request(){
  System.out.println("before");
  try {
    realSubject.request();
  }
 catch (  Exception e) {
    System.out.println("ex:" + e.getMessage());
    throw e;
  }
 finally {
    System.out.println("after");
  }
}
