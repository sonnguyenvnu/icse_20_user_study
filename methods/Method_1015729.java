public synchronized Runner start(){
  if (running)   return this;
  if (thread == null || !thread.isAlive()) {
    String name=thread_name != null ? thread_name : "runner";
    thread=factory != null ? factory.newThread(this,name) : new Thread(this,name);
    thread.setDaemon(daemon);
    running=true;
    thread.start();
  }
  return this;
}
