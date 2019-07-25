/** 
 * Shrink the file so there are no empty pages at the end.
 * @param compactMode 0 if no compacting should happen, otherwiseTransactionCommand.SHUTDOWN_COMPACT or TransactionCommand.SHUTDOWN_DEFRAG
 */
public synchronized void compact(int compactMode){
  if (!database.getSettings().pageStoreTrim) {
    return;
  }
  if (SysProperties.MODIFY_ON_WRITE && readMode && compactMode == 0) {
    return;
  }
  openForWriting();
  int lastUsed=-1;
  for (int i=getFreeListId(pageCount); i >= 0; i--) {
    lastUsed=getFreeList(i).getLastUsed();
    if (lastUsed != -1) {
      break;
    }
  }
  writeBack();
  log.free();
  recoveryRunning=true;
  try {
    logFirstTrunkPage=lastUsed + 1;
    allocatePage(logFirstTrunkPage);
    log.openForWriting(logFirstTrunkPage,true);
    log.checkpoint();
  }
  finally {
    recoveryRunning=false;
  }
  long start=System.nanoTime();
  boolean isCompactFully=compactMode == CommandInterface.SHUTDOWN_COMPACT;
  boolean isDefrag=compactMode == CommandInterface.SHUTDOWN_DEFRAG;
  if (database.getSettings().defragAlways) {
    isCompactFully=isDefrag=true;
  }
  int maxCompactTime=database.getSettings().maxCompactTime;
  int maxMove=database.getSettings().maxCompactCount;
  if (isCompactFully || isDefrag) {
    maxCompactTime=Integer.MAX_VALUE;
    maxMove=Integer.MAX_VALUE;
  }
  int blockSize=isCompactFully ? COMPACT_BLOCK_SIZE : 1;
  int firstFree=MIN_PAGE_COUNT;
  for (int x=lastUsed, j=0; x > MIN_PAGE_COUNT && j < maxMove; x-=blockSize) {
    for (int full=x - blockSize + 1; full <= x; full++) {
      if (full > MIN_PAGE_COUNT && isUsed(full)) {
synchronized (this) {
          firstFree=getFirstFree(firstFree);
          if (firstFree == -1 || firstFree >= full) {
            j=maxMove;
            break;
          }
          if (compact(full,firstFree)) {
            j++;
            long now=System.nanoTime();
            if (now > start + TimeUnit.MILLISECONDS.toNanos(maxCompactTime)) {
              j=maxMove;
              break;
            }
          }
        }
      }
    }
  }
  if (isDefrag) {
    log.checkpoint();
    writeBack();
    cache.clear();
    ArrayList<Table> tables=database.getAllTablesAndViews(false);
    recordedPagesList=new ArrayList<>();
    recordedPagesIndex=new IntIntHashMap();
    recordPageReads=true;
    Session sysSession=database.getSystemSession();
    for (    Table table : tables) {
      if (!table.isTemporary() && TableType.TABLE == table.getTableType()) {
        Index scanIndex=table.getScanIndex(sysSession);
        Cursor cursor=scanIndex.find(sysSession,null,null);
        while (cursor.next()) {
          cursor.get();
        }
        for (        Index index : table.getIndexes()) {
          if (index != scanIndex && index.canScan()) {
            cursor=index.find(sysSession,null,null);
            while (cursor.next()) {
            }
          }
        }
      }
    }
    recordPageReads=false;
    int target=MIN_PAGE_COUNT - 1;
    int temp=0;
    for (int i=0, size=recordedPagesList.size(); i < size; i++) {
      log.checkpoint();
      writeBack();
      int source=recordedPagesList.get(i);
      Page pageSource=getPage(source);
      if (!pageSource.canMove()) {
        continue;
      }
      while (true) {
        Page pageTarget=getPage(++target);
        if (pageTarget == null || pageTarget.canMove()) {
          break;
        }
      }
      if (target == source) {
        continue;
      }
      temp=getFirstFree(temp);
      if (temp == -1) {
        DbException.throwInternalError("no free page for defrag");
      }
      cache.clear();
      swap(source,target,temp);
      int index=recordedPagesIndex.get(target);
      if (index != IntIntHashMap.NOT_FOUND) {
        recordedPagesList.set(index,source);
        recordedPagesIndex.put(source,index);
      }
      recordedPagesList.set(i,target);
      recordedPagesIndex.put(target,i);
    }
    recordedPagesList=null;
    recordedPagesIndex=null;
  }
  checkpoint();
  log.checkpoint();
  writeIndexRowCounts();
  log.checkpoint();
  writeBack();
  commit(pageStoreSession);
  writeBack();
  log.checkpoint();
  log.free();
  recoveryRunning=true;
  try {
    setLogFirstPage(++logKey,0,0);
  }
  finally {
    recoveryRunning=false;
  }
  writeBack();
  for (int i=getFreeListId(pageCount); i >= 0; i--) {
    lastUsed=getFreeList(i).getLastUsed();
    if (lastUsed != -1) {
      break;
    }
  }
  int newPageCount=lastUsed + 1;
  if (newPageCount < pageCount) {
    freed.set(newPageCount,pageCount,false);
  }
  pageCount=newPageCount;
  freeLists.clear();
  trace.debug("pageCount: " + pageCount);
  long newLength=(long)pageCount << pageSizeShift;
  if (file.length() != newLength) {
    file.setLength(newLength);
    writeCount++;
  }
}
