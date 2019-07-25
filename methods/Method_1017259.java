@Timeout public void scheduler(Timer timer){
  Date currentTime=new Date();
  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
  System.out.println("TimeoutExample.scheduler() " + timer.getInfo() + simpleDateFormat.format(currentTime));
}
