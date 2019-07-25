public static ExecutorService setup(ExecutorService es){
  if (Sender.es != null)   shutdown();
  if (es == null)   es=Executors.newFixedThreadPool(64);
  Sender.es=es;
  return es;
}
