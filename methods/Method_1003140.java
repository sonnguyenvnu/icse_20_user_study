/** 
 * Add, replace or remove a key-value pair.
 * @param key the key (may not be null)
 * @param value new value, it may be null when removal is intended
 * @param decisionMaker command object to make choices during transaction.
 * @return previous value, if mapping for that key existed, or null otherwise
 */
@SuppressWarnings("unchecked") public V operate(K key,V value,DecisionMaker<? super V> decisionMaker){
  IntValueHolder unsavedMemoryHolder=new IntValueHolder();
  int attempt=0;
  while (true) {
    RootReference rootReference=flushAndGetRoot();
    boolean locked=rootReference.isLockedByCurrentThread();
    if (!locked) {
      if (attempt++ == 0) {
        beforeWrite();
      }
 else       if (attempt > 3 || rootReference.isLocked()) {
        rootReference=lockRoot(rootReference,attempt);
        locked=true;
      }
    }
    Page rootPage=rootReference.root;
    long version=rootReference.version;
    CursorPos tip;
    V result;
    unsavedMemoryHolder.value=0;
    try {
      CursorPos pos=CursorPos.traverseDown(rootPage,key);
      if (!locked && rootReference != getRoot()) {
        continue;
      }
      Page p=pos.page;
      int index=pos.index;
      tip=pos;
      pos=pos.parent;
      result=index < 0 ? null : (V)p.getValue(index);
      Decision decision=decisionMaker.decide(result,value);
switch (decision) {
case REPEAT:
        decisionMaker.reset();
      continue;
case ABORT:
    if (!locked && rootReference != getRoot()) {
      decisionMaker.reset();
      continue;
    }
  return result;
case REMOVE:
{
  if (index < 0) {
    if (!locked && rootReference != getRoot()) {
      decisionMaker.reset();
      continue;
    }
    return null;
  }
  if (p.getTotalCount() == 1 && pos != null) {
    int keyCount;
    do {
      p=pos.page;
      index=pos.index;
      pos=pos.parent;
      keyCount=p.getKeyCount();
    }
 while (keyCount == 0 && pos != null);
    if (keyCount <= 1) {
      if (keyCount == 1) {
        assert index <= 1;
        p=p.getChildPage(1 - index);
      }
 else {
        p=Page.createEmptyLeaf(this);
      }
      break;
    }
  }
  p=p.copy();
  p.remove(index);
  break;
}
case PUT:
{
value=decisionMaker.selectValue(result,value);
p=p.copy();
if (index < 0) {
  p.insertLeaf(-index - 1,key,value);
  int keyCount;
  while ((keyCount=p.getKeyCount()) > store.getKeysPerPage() || p.getMemory() > store.getMaxPageSize() && keyCount > (p.isLeaf() ? 1 : 2)) {
    long totalCount=p.getTotalCount();
    int at=keyCount >> 1;
    Object k=p.getKey(at);
    Page split=p.split(at);
    unsavedMemoryHolder.value+=p.getMemory() + split.getMemory();
    if (pos == null) {
      Object[] keys={k};
      Page.PageReference[] children={new Page.PageReference(p),new Page.PageReference(split)};
      p=Page.createNode(this,keys,children,totalCount,0);
      break;
    }
    Page c=p;
    p=pos.page;
    index=pos.index;
    pos=pos.parent;
    p=p.copy();
    p.setChild(index,split);
    p.insertNode(index,k,c);
  }
}
 else {
  p.setValue(index,value);
}
break;
}
}
rootPage=replacePage(pos,p,unsavedMemoryHolder);
if (!locked) {
if (!isPersistent()) {
if (updateRoot(rootReference,rootPage,attempt)) {
notifyWaiters();
return result;
}
 else {
decisionMaker.reset();
continue;
}
}
 else {
locked=tryLock(rootReference,attempt) != null;
if (!locked) {
decisionMaker.reset();
continue;
}
}
}
store.registerUnsavedMemory(unsavedMemoryHolder.value + tip.processRemovalInfo(version));
return result;
}
  finally {
if (locked) {
unlockRoot(rootPage);
}
}
}
}
