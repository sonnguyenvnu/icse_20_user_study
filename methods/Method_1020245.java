public static synchronized void configure(RandomCodeStrategy custom){
  if (strategy == custom)   return;
  if (strategy != null)   strategy.release();
  strategy=custom;
}
