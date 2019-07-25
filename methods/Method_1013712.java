protected boolean publish(){
  String cluster=getHolder().clusterName();
  String newPrimaryDc=getHolder().destDc();
  List<InetSocketAddress> newMasters=getNewMasters();
  boolean ret=false;
  MigrationPublishResult res=null;
  try {
    res=getMigrationPublishService().doMigrationPublish(cluster,newPrimaryDc,newMasters);
    logger.info("[MigrationPublishStat][result]{}",res);
    ret=res.isSuccess();
  }
 catch (  Exception e) {
    res=new MigrationPublishResult("",cluster,newPrimaryDc,newMasters);
    res.setSuccess(false);
    res.setMessage(e.getMessage());
    logger.error("[MigrationPublish][fail]",e);
    ret=false;
  }
  updateMigrationPublishResult(res);
  return ret;
}
