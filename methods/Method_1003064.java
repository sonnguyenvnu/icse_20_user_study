private synchronized void open(int traceLevelFile,int traceLevelSystemOut,ConnectionInfo ci){
  if (persistent) {
    String dataFileName=databaseName + Constants.SUFFIX_OLD_DATABASE_FILE;
    boolean existsData=FileUtils.exists(dataFileName);
    String pageFileName=databaseName + Constants.SUFFIX_PAGE_FILE;
    String mvFileName=databaseName + Constants.SUFFIX_MV_FILE;
    boolean existsPage=FileUtils.exists(pageFileName);
    boolean existsMv=FileUtils.exists(mvFileName);
    if (existsData && (!existsPage && !existsMv)) {
      throw DbException.get(ErrorCode.FILE_VERSION_ERROR_1,"Old database: " + dataFileName + " - please convert the database " + "to a SQL script and re-create it.");
    }
    if (existsPage && !FileUtils.canWrite(pageFileName)) {
      readOnly=true;
    }
    if (existsMv && !FileUtils.canWrite(mvFileName)) {
      readOnly=true;
    }
    if (existsPage && !existsMv) {
      dbSettings.setMvStore(false);
      multiThreaded=ci.getProperty("MULTI_THREADED",false);
    }
    if (readOnly) {
      if (traceLevelFile >= TraceSystem.DEBUG) {
        String traceFile=Utils.getProperty("java.io.tmpdir",".") + "/" + "h2_" + System.currentTimeMillis();
        traceSystem=new TraceSystem(traceFile + Constants.SUFFIX_TRACE_FILE);
      }
 else {
        traceSystem=new TraceSystem(null);
      }
    }
 else {
      traceSystem=new TraceSystem(databaseName + Constants.SUFFIX_TRACE_FILE);
    }
    traceSystem.setLevelFile(traceLevelFile);
    traceSystem.setLevelSystemOut(traceLevelSystemOut);
    trace=traceSystem.getTrace(Trace.DATABASE);
    trace.info("opening {0} (build {1})",databaseName,Constants.BUILD_ID);
    if (autoServerMode) {
      if (readOnly || fileLockMethod == FileLockMethod.NO || fileLockMethod == FileLockMethod.SERIALIZED || fileLockMethod == FileLockMethod.FS) {
        throw DbException.getUnsupportedException("autoServerMode && (readOnly || " + "fileLockMethod == NO || " + "fileLockMethod == SERIALIZED || " + "fileLockMethod == FS || " + "inMemory)");
      }
    }
    String lockFileName=databaseName + Constants.SUFFIX_LOCK_FILE;
    if (readOnly) {
      if (FileUtils.exists(lockFileName)) {
        throw DbException.get(ErrorCode.DATABASE_ALREADY_OPEN_1,"Lock file exists: " + lockFileName);
      }
    }
    if (!readOnly && fileLockMethod != FileLockMethod.NO) {
      if (fileLockMethod != FileLockMethod.FS) {
        lock=new FileLock(traceSystem,lockFileName,Constants.LOCK_SLEEP);
        lock.lock(fileLockMethod);
        if (autoServerMode) {
          startServer(lock.getUniqueId());
        }
      }
    }
    if (SysProperties.MODIFY_ON_WRITE) {
      while (isReconnectNeeded()) {
      }
    }
 else {
      while (isReconnectNeeded() && !beforeWriting()) {
      }
    }
    deleteOldTempFiles();
    starting=true;
    if (SysProperties.MODIFY_ON_WRITE) {
      try {
        getPageStore();
      }
 catch (      DbException e) {
        if (e.getErrorCode() != ErrorCode.DATABASE_IS_READ_ONLY) {
          throw e;
        }
        pageStore=null;
        while (!beforeWriting()) {
        }
        getPageStore();
      }
    }
 else {
      getPageStore();
    }
    starting=false;
    if (store == null) {
      writer=WriterThread.create(this,writeDelay);
    }
 else {
      setWriteDelay(writeDelay);
    }
  }
 else {
    if (autoServerMode) {
      throw DbException.getUnsupportedException("autoServerMode && inMemory");
    }
    traceSystem=new TraceSystem(null);
    trace=traceSystem.getTrace(Trace.DATABASE);
    if (dbSettings.mvStore) {
      getPageStore();
    }
  }
  if (store != null) {
    store.getTransactionStore().init();
  }
  systemUser=new User(this,0,SYSTEM_USER_NAME,true);
  mainSchema=new Schema(this,Constants.MAIN_SCHEMA_ID,sysIdentifier(Constants.SCHEMA_MAIN),systemUser,true);
  infoSchema=new Schema(this,Constants.INFORMATION_SCHEMA_ID,sysIdentifier("INFORMATION_SCHEMA"),systemUser,true);
  schemas.put(mainSchema.getName(),mainSchema);
  schemas.put(infoSchema.getName(),infoSchema);
  publicRole=new Role(this,0,sysIdentifier(Constants.PUBLIC_ROLE_NAME),true);
  roles.put(publicRole.getName(),publicRole);
  systemUser.setAdmin(true);
  systemSession=new Session(this,systemUser,++nextSessionId);
  lobSession=new Session(this,systemUser,++nextSessionId);
  CreateTableData data=new CreateTableData();
  ArrayList<Column> cols=data.columns;
  Column columnId=new Column("ID",Value.INT);
  columnId.setNullable(false);
  cols.add(columnId);
  cols.add(new Column("HEAD",Value.INT));
  cols.add(new Column("TYPE",Value.INT));
  cols.add(new Column("SQL",Value.STRING));
  boolean create=true;
  if (pageStore != null) {
    create=pageStore.isNew();
  }
  data.tableName="SYS";
  data.id=0;
  data.temporary=false;
  data.persistData=persistent;
  data.persistIndexes=persistent;
  data.create=create;
  data.isHidden=true;
  data.session=systemSession;
  starting=true;
  meta=mainSchema.createTable(data);
  handleUpgradeIssues();
  IndexColumn[] pkCols=IndexColumn.wrap(new Column[]{columnId});
  metaIdIndex=meta.addIndex(systemSession,"SYS_ID",0,pkCols,IndexType.createPrimaryKey(false,false),true,null);
  systemSession.commit(true);
  objectIds.set(0);
  Cursor cursor=metaIdIndex.find(systemSession,null,null);
  ArrayList<MetaRecord> records=new ArrayList<>((int)metaIdIndex.getRowCountApproximation());
  while (cursor.next()) {
    MetaRecord rec=new MetaRecord(cursor.get());
    objectIds.set(rec.getId());
    records.add(rec);
  }
  Collections.sort(records);
synchronized (systemSession) {
    for (    MetaRecord rec : records) {
      rec.execute(this,systemSession,eventListener);
    }
  }
  systemSession.commit(true);
  if (store != null) {
    store.getTransactionStore().endLeftoverTransactions();
    store.removeTemporaryMaps(objectIds);
  }
  recompileInvalidViews(systemSession);
  starting=false;
  if (!readOnly) {
    String name=SetTypes.getTypeName(SetTypes.CREATE_BUILD);
    if (settings.get(name) == null) {
      Setting setting=new Setting(this,allocateObjectId(),name);
      setting.setIntValue(Constants.BUILD_ID);
      lockMeta(systemSession);
      addDatabaseObject(systemSession,setting);
    }
    setSortSetting(SetTypes.BINARY_COLLATION,SysProperties.SORT_BINARY_UNSIGNED,true);
    setSortSetting(SetTypes.UUID_COLLATION,SysProperties.SORT_UUID_UNSIGNED,false);
    if (pageStore != null) {
      BitSet f=pageStore.getObjectIds();
      for (int i=0, len=f.length(); i < len; i++) {
        if (f.get(i) && !objectIds.get(i)) {
          trace.info("unused object id: " + i);
          objectIds.set(i);
        }
      }
    }
  }
  getLobStorage().init();
  systemSession.commit(true);
  trace.info("opened {0}",databaseName);
  if (checkpointAllowed > 0) {
    afterWriting();
  }
}
