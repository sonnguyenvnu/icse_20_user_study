public static void schedule(MultistepProxyTransformer multistepProxyTransformer){
  new Thread(new RedefinitionScheduler(multistepProxyTransformer)).start();
}
