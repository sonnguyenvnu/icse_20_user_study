protected <T extends WebCrawler>void start(final WebCrawlerFactory<T> crawlerFactory,final int numberOfCrawlers,boolean isBlocking){
  try {
    finished=false;
    setError(null);
    crawlersLocalData.clear();
    final List<Thread> threads=new ArrayList<>();
    final List<T> crawlers=new ArrayList<>();
    for (int i=1; i <= numberOfCrawlers; i++) {
      T crawler=crawlerFactory.newInstance();
      Thread thread=new Thread(crawler,"Crawler " + i);
      crawler.setThread(thread);
      crawler.init(i,this);
      thread.start();
      crawlers.add(crawler);
      threads.add(thread);
      logger.info("Crawler {} started",i);
    }
    final CrawlController controller=this;
    Thread monitorThread=new Thread(new Runnable(){
      @Override public void run(){
        try {
synchronized (waitingLock) {
            while (true) {
              sleep(config.getThreadMonitoringDelaySeconds());
              boolean someoneIsWorking=false;
              for (int i=0; i < threads.size(); i++) {
                Thread thread=threads.get(i);
                if (!thread.isAlive()) {
                  if (!shuttingDown && !config.isHaltOnError()) {
                    logger.info("Thread {} was dead, I'll recreate it",i);
                    T crawler=crawlerFactory.newInstance();
                    thread=new Thread(crawler,"Crawler " + (i + 1));
                    threads.remove(i);
                    threads.add(i,thread);
                    crawler.setThread(thread);
                    crawler.init(i + 1,controller);
                    thread.start();
                    crawlers.remove(i);
                    crawlers.add(i,crawler);
                  }
                }
 else                 if (crawlers.get(i).isNotWaitingForNewURLs()) {
                  someoneIsWorking=true;
                }
                Throwable t=crawlers.get(i).getError();
                if (t != null && config.isHaltOnError()) {
                  throw new RuntimeException("error on thread [" + threads.get(i).getName() + "]",t);
                }
              }
              boolean shutOnEmpty=config.isShutdownOnEmptyQueue();
              if (!someoneIsWorking && shutOnEmpty) {
                logger.info("It looks like no thread is working, waiting for " + config.getThreadShutdownDelaySeconds() + " seconds to make sure...");
                sleep(config.getThreadShutdownDelaySeconds());
                someoneIsWorking=false;
                for (int i=0; i < threads.size(); i++) {
                  Thread thread=threads.get(i);
                  if (thread.isAlive() && crawlers.get(i).isNotWaitingForNewURLs()) {
                    someoneIsWorking=true;
                  }
                }
                if (!someoneIsWorking) {
                  if (!shuttingDown) {
                    long queueLength=frontier.getQueueLength();
                    if (queueLength > 0) {
                      continue;
                    }
                    logger.info("No thread is working and no more URLs are in " + "queue waiting for another " + config.getThreadShutdownDelaySeconds() + " seconds to make sure...");
                    sleep(config.getThreadShutdownDelaySeconds());
                    queueLength=frontier.getQueueLength();
                    if (queueLength > 0) {
                      continue;
                    }
                  }
                  logger.info("All of the crawlers are stopped. Finishing the " + "process...");
                  frontier.finish();
                  for (                  T crawler : crawlers) {
                    crawler.onBeforeExit();
                    crawlersLocalData.add(crawler.getMyLocalData());
                  }
                  logger.info("Waiting for " + config.getCleanupDelaySeconds() + " seconds before final clean up...");
                  sleep(config.getCleanupDelaySeconds());
                  frontier.close();
                  docIdServer.close();
                  pageFetcher.shutDown();
                  finished=true;
                  waitingLock.notifyAll();
                  env.close();
                  return;
                }
              }
            }
          }
        }
 catch (        Throwable e) {
          if (config.isHaltOnError()) {
            setError(e);
synchronized (waitingLock) {
              frontier.finish();
              frontier.close();
              docIdServer.close();
              pageFetcher.shutDown();
              waitingLock.notifyAll();
              env.close();
            }
          }
 else {
            logger.error("Unexpected Error",e);
          }
        }
      }
    }
);
    monitorThread.start();
    if (isBlocking) {
      waitUntilFinish();
    }
  }
 catch (  Exception e) {
    if (config.isHaltOnError()) {
      if (e instanceof RuntimeException) {
        throw (RuntimeException)e;
      }
 else {
        throw new RuntimeException("error running the monitor thread",e);
      }
    }
 else {
      logger.error("Error happened",e);
    }
  }
}
