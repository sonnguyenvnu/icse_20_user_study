private void init(HoodieRecord record){
  if (doInit) {
    this.partitionPath=record.getPartitionPath();
    RealtimeView rtView=hoodieTable.getRTFileSystemView();
    Option<FileSlice> fileSlice=rtView.getLatestFileSlice(partitionPath,fileId);
    String baseInstantTime=instantTime;
    if (fileSlice.isPresent()) {
      baseInstantTime=fileSlice.get().getBaseInstantTime();
    }
 else {
      fileSlice=Option.of(new FileSlice(partitionPath,baseInstantTime,this.fileId));
      logger.info("New InsertHandle for partition :" + partitionPath);
    }
    writeStatus.getStat().setPrevCommit(baseInstantTime);
    writeStatus.setFileId(fileId);
    writeStatus.setPartitionPath(partitionPath);
    writeStatus.getStat().setPartitionPath(partitionPath);
    writeStatus.getStat().setFileId(fileId);
    averageRecordSize=SizeEstimator.estimate(record);
    try {
      this.writer=createLogWriter(fileSlice,baseInstantTime);
      this.currentLogFile=writer.getLogFile();
      ((HoodieDeltaWriteStat)writeStatus.getStat()).setLogVersion(currentLogFile.getLogVersion());
      ((HoodieDeltaWriteStat)writeStatus.getStat()).setLogOffset(writer.getCurrentSize());
    }
 catch (    Exception e) {
      logger.error("Error in update task at commit " + instantTime,e);
      writeStatus.setGlobalError(e);
      throw new HoodieUpsertException("Failed to initialize HoodieAppendHandle for FileId: " + fileId + " on commit " + instantTime + " on HDFS path " + hoodieTable.getMetaClient().getBasePath() + partitionPath,e);
    }
    Path path=new Path(partitionPath,writer.getLogFile().getFileName());
    writeStatus.getStat().setPath(path.toString());
    doInit=false;
  }
}
