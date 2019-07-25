/** 
 * Run one recovery stage. There are three recovery stages: 0: only the undo steps are run (restoring the state before the last checkpoint). 1: the pages that are used by the transaction log are allocated. 2: the committed operations are re-applied.
 * @param stage the recovery stage
 * @return whether the transaction log was empty
 */
boolean recover(int stage){
  if (trace.isDebugEnabled()) {
    trace.debug("log recover stage: " + stage);
  }
  if (stage == RECOVERY_STAGE_ALLOCATE) {
    PageInputStream in=new PageInputStream(store,logKey,firstTrunkPage,firstDataPage);
    usedLogPages=in.allocateAllPages();
    in.close();
    return true;
  }
  PageInputStream pageIn=new PageInputStream(store,logKey,firstTrunkPage,firstDataPage);
  DataReader in=new DataReader(pageIn);
  int logId=0;
  Data data=store.createData();
  boolean isEmpty=true;
  try {
    int pos=0;
    while (true) {
      int x=in.readByte();
      if (x < 0) {
        break;
      }
      pos++;
      isEmpty=false;
      if (x == UNDO) {
        int pageId=in.readVarInt();
        int size=in.readVarInt();
        if (size == 0) {
          in.readFully(data.getBytes(),store.getPageSize());
        }
 else         if (size == 1) {
          Arrays.fill(data.getBytes(),0,store.getPageSize(),(byte)0);
        }
 else {
          in.readFully(compressBuffer,size);
          try {
            compress.expand(compressBuffer,0,size,data.getBytes(),0,store.getPageSize());
          }
 catch (          ArrayIndexOutOfBoundsException e) {
            DbException.convertToIOException(e);
          }
        }
        if (stage == RECOVERY_STAGE_UNDO) {
          if (!undo.get(pageId)) {
            if (trace.isDebugEnabled()) {
              trace.debug("log undo {0}",pageId);
            }
            store.writePage(pageId,data);
            undo.set(pageId);
            undoAll.set(pageId);
          }
 else {
            if (trace.isDebugEnabled()) {
              trace.debug("log undo skip {0}",pageId);
            }
          }
        }
      }
 else       if (x == ADD) {
        int sessionId=in.readVarInt();
        int tableId=in.readVarInt();
        Row row=readRow(store.getDatabase().getRowFactory(),in,data);
        if (stage == RECOVERY_STAGE_UNDO) {
          store.allocateIfIndexRoot(pos,tableId,row);
        }
 else         if (stage == RECOVERY_STAGE_REDO) {
          if (isSessionCommitted(sessionId,logId,pos)) {
            if (trace.isDebugEnabled()) {
              trace.debug("log redo + table: " + tableId + " s: " + sessionId + " " + row);
            }
            store.redo(tableId,row,true);
          }
 else {
            if (trace.isDebugEnabled()) {
              trace.debug("log ignore s: " + sessionId + " + table: " + tableId + " " + row);
            }
          }
        }
      }
 else       if (x == REMOVE) {
        int sessionId=in.readVarInt();
        int tableId=in.readVarInt();
        long key=in.readVarLong();
        if (stage == RECOVERY_STAGE_REDO) {
          if (isSessionCommitted(sessionId,logId,pos)) {
            if (trace.isDebugEnabled()) {
              trace.debug("log redo - table: " + tableId + " s:" + sessionId + " key: " + key);
            }
            store.redoDelete(tableId,key);
          }
 else {
            if (trace.isDebugEnabled()) {
              trace.debug("log ignore s: " + sessionId + " - table: " + tableId + " " + key);
            }
          }
        }
      }
 else       if (x == TRUNCATE) {
        int sessionId=in.readVarInt();
        int tableId=in.readVarInt();
        if (stage == RECOVERY_STAGE_REDO) {
          if (isSessionCommitted(sessionId,logId,pos)) {
            if (trace.isDebugEnabled()) {
              trace.debug("log redo truncate table: " + tableId);
            }
            store.redoTruncate(tableId);
          }
 else {
            if (trace.isDebugEnabled()) {
              trace.debug("log ignore s: " + sessionId + " truncate table: " + tableId);
            }
          }
        }
      }
 else       if (x == PREPARE_COMMIT) {
        int sessionId=in.readVarInt();
        String transaction=in.readString();
        if (trace.isDebugEnabled()) {
          trace.debug("log prepare commit " + sessionId + " " + transaction + " pos: " + pos);
        }
        if (stage == RECOVERY_STAGE_UNDO) {
          int page=pageIn.getDataPage();
          setPrepareCommit(sessionId,page,transaction);
        }
      }
 else       if (x == ROLLBACK) {
        int sessionId=in.readVarInt();
        if (trace.isDebugEnabled()) {
          trace.debug("log rollback " + sessionId + " pos: " + pos);
        }
      }
 else       if (x == COMMIT) {
        int sessionId=in.readVarInt();
        if (trace.isDebugEnabled()) {
          trace.debug("log commit " + sessionId + " pos: " + pos);
        }
        if (stage == RECOVERY_STAGE_UNDO) {
          setLastCommitForSession(sessionId,logId,pos);
        }
      }
 else       if (x == NOOP) {
      }
 else       if (x == CHECKPOINT) {
        logId++;
      }
 else       if (x == FREE_LOG) {
        int count=in.readVarInt();
        for (int i=0; i < count; i++) {
          int pageId=in.readVarInt();
          if (stage == RECOVERY_STAGE_REDO) {
            if (!usedLogPages.get(pageId)) {
              store.free(pageId,false);
            }
          }
        }
      }
 else {
        if (trace.isDebugEnabled()) {
          trace.debug("log end");
          break;
        }
      }
    }
  }
 catch (  DbException e) {
    if (e.getErrorCode() == ErrorCode.FILE_CORRUPTED_1) {
      trace.debug("log recovery stopped");
    }
 else {
      throw e;
    }
  }
catch (  IOException e) {
    trace.debug("log recovery completed");
  }
  undo=new BitSet();
  if (stage == RECOVERY_STAGE_REDO) {
    usedLogPages=null;
  }
  return isEmpty;
}
