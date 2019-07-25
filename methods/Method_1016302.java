/** 
 * Executes the given pathfinding request resuming it if needed.
 * @param request the pathfinding request
 * @return {@code true} if this operation has completed; {@code false} if more time is needed to complete. 
 */
public boolean execute(PathFinderRequest<N> request){
  request.executionFrames++;
  while (true) {
    if (DEBUG)     GdxAI.getLogger().debug(TAG,"------");
    if (request.status == PathFinderRequest.SEARCH_NEW) {
      long currentTime=TimeUtils.nanoTime();
      timeToRun-=currentTime - lastTime;
      if (timeToRun <= timeTolerance)       return false;
      if (DEBUG)       GdxAI.getLogger().debug(TAG,"search begin");
      if (!request.initializeSearch(timeToRun))       return false;
      request.changeStatus(PathFinderRequest.SEARCH_INITIALIZED);
      lastTime=currentTime;
    }
    if (request.status == PathFinderRequest.SEARCH_INITIALIZED) {
      long currentTime=TimeUtils.nanoTime();
      timeToRun-=currentTime - lastTime;
      if (timeToRun <= timeTolerance)       return false;
      if (DEBUG)       GdxAI.getLogger().debug(TAG,"search path");
      if (!request.search(pathFinder,timeToRun))       return false;
      request.changeStatus(PathFinderRequest.SEARCH_DONE);
      lastTime=currentTime;
    }
    if (request.status == PathFinderRequest.SEARCH_DONE) {
      long currentTime=TimeUtils.nanoTime();
      timeToRun-=currentTime - lastTime;
      if (timeToRun <= timeTolerance)       return false;
      if (DEBUG)       GdxAI.getLogger().debug(TAG,"search end");
      if (!request.finalizeSearch(timeToRun))       return false;
      request.changeStatus(PathFinderRequest.SEARCH_FINALIZED);
      if (server != null) {
        MessageDispatcher dispatcher=request.dispatcher != null ? request.dispatcher : MessageManager.getInstance();
        dispatcher.dispatchMessage(server,request.client,request.responseMessageCode,request);
      }
      lastTime=currentTime;
      if (request.statusChanged && request.status == PathFinderRequest.SEARCH_NEW) {
        if (DEBUG)         GdxAI.getLogger().debug(TAG,"search renew");
        continue;
      }
    }
    return true;
  }
}
