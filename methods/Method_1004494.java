public void clean(){
  logManager.deleteExpiredSegments(config.getMessageLogRetentionMs(),(logManager,segment) -> {
    consumerLogManager.adjustConsumerLogMinOffset(logManager.firstSegment());
    final String fileName=StoreUtils.offsetFileNameForSegment(segment);
    final String path=config.getMessageLogStorePath();
    final File file=new File(path,fileName);
    try {
      if (!file.delete()) {
        LOG.warn("delete offset file failed. file: {}",fileName);
      }
    }
 catch (    Exception e) {
      LOG.warn("delete offset file failed.. file: {}",fileName,e);
    }
  }
);
}
