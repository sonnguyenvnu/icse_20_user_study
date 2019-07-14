@Override public void rejectedExecution(Runnable r,ThreadPoolExecutor executor){
  if (LOGGER.isWarnEnabled()) {
    LOGGER.warn(LogCodes.getLog(LogCodes.ERROR_PROVIDER_TR_POOL_REJECTION,executor.getActiveCount(),executor.getPoolSize(),executor.getLargestPoolSize(),executor.getCorePoolSize(),executor.getMaximumPoolSize(),executor.getQueue().size(),executor.getQueue().remainingCapacity()));
  }
  throw new RejectedExecutionException();
}
