public void recover(TransactionRecoveryCallback callback){
  MongoCursor<Document> transactionCursor=null;
  try {
    String application=CommonUtils.getApplication(this.endpoint);
    String databaseName=application.replaceAll("\\W","_");
    MongoDatabase mdb=this.mongoClient.getDatabase(databaseName);
    MongoCollection<Document> transactions=mdb.getCollection(CONSTANTS_TB_TRANSACTIONS);
    FindIterable<Document> transactionItr=transactions.find(Filters.eq("coordinator",true));
    for (transactionCursor=transactionItr.iterator(); transactionCursor.hasNext(); ) {
      Document document=transactionCursor.next();
      boolean error=document.getBoolean("error");
      String targetApplication=document.getString("system");
      long expectVersion=document.getLong("version");
      long actualVersion=this.versionManager.getInstanceVersion(targetApplication);
      if (error == false && actualVersion > 0 && actualVersion <= expectVersion) {
        continue;
      }
      callback.recover(this.reconstructTransactionArchive(document));
    }
  }
 catch (  RuntimeException error) {
    logger.error("Error occurred while recovering transaction.",error);
  }
catch (  Exception error) {
    logger.error("Error occurred while recovering transaction.",error);
  }
 finally {
    IOUtils.closeQuietly(transactionCursor);
  }
}
