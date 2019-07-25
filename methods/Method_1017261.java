@PreDestroy public void stop(){
  System.out.println("EJB Timer: Stop timers.");
  for (  Timer timer : timerService.getTimers()) {
    System.out.println("Stopping timer: " + timer.getInfo());
    timer.cancel();
  }
}
