public void getNextURLs(int max,List<WebURL> result){
  while (true) {
synchronized (mutex) {
      if (isFinished) {
        return;
      }
      try {
        List<WebURL> curResults=workQueues.get(max);
        workQueues.delete(curResults.size());
        if (inProcessPages != null) {
          for (          WebURL curPage : curResults) {
            inProcessPages.put(curPage);
          }
        }
        result.addAll(curResults);
      }
 catch (      DatabaseException e) {
        logger.error("Error while getting next urls",e);
      }
      if (result.size() > 0) {
        return;
      }
    }
    try {
synchronized (waitingList) {
        waitingList.wait();
      }
    }
 catch (    InterruptedException ignored) {
    }
    if (isFinished) {
      return;
    }
  }
}
