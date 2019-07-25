/** 
 * Extract old file path, initialize StorageWriter and WriteStatus
 */
private void init(String fileId,String partitionPath,HoodieDataFile dataFileToBeMerged){
  this.writtenRecordKeys=new HashSet<>();
  writeStatus.setStat(new HoodieWriteStat());
  try {
    String latestValidFilePath=dataFileToBeMerged.getFileName();
    writeStatus.getStat().setPrevCommit(FSUtils.getCommitTime(latestValidFilePath));
    HoodiePartitionMetadata partitionMetadata=new HoodiePartitionMetadata(fs,instantTime,new Path(config.getBasePath()),FSUtils.getPartitionPath(config.getBasePath(),partitionPath));
    partitionMetadata.trySave(TaskContext.getPartitionId());
    oldFilePath=new Path(config.getBasePath() + "/" + partitionPath + "/" + latestValidFilePath);
    String relativePath=new Path((partitionPath.isEmpty() ? "" : partitionPath + "/") + FSUtils.makeDataFileName(instantTime,writeToken,fileId)).toString();
    newFilePath=new Path(config.getBasePath(),relativePath);
    logger.info(String.format("Merging new data into oldPath %s, as newPath %s",oldFilePath.toString(),newFilePath.toString()));
    writeStatus.setFileId(fileId);
    writeStatus.setPartitionPath(partitionPath);
    writeStatus.getStat().setPartitionPath(partitionPath);
    writeStatus.getStat().setFileId(fileId);
    writeStatus.getStat().setPath(new Path(config.getBasePath()),newFilePath);
    createMarkerFile(partitionPath);
    storageWriter=HoodieStorageWriterFactory.getStorageWriter(instantTime,newFilePath,hoodieTable,config,writerSchema);
  }
 catch (  IOException io) {
    logger.error("Error in update task at commit " + instantTime,io);
    writeStatus.setGlobalError(io);
    throw new HoodieUpsertException("Failed to initialize HoodieUpdateHandle for FileId: " + fileId + " on commit " + instantTime + " on path " + hoodieTable.getMetaClient().getBasePath(),io);
  }
}
