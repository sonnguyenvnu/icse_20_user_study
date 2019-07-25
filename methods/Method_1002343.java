public void recover(TransactionRecoveryCallback callback){
  final Map<Xid,TransactionArchive> xidMap=new HashMap<Xid,TransactionArchive>();
  final ArchiveDeserializer deserializer=this.beanFactory.getArchiveDeserializer();
  final XidFactory xidFactory=this.beanFactory.getCompensableXidFactory();
  this.traversal(new VirtualLoggingListener(){
    public void recvOperation(    VirtualLoggingRecord action){
      Xid xid=action.getIdentifier();
      int operator=action.getOperator();
      if (VirtualLoggingSystem.OPERATOR_DELETE == operator) {
        xidMap.remove(xid);
      }
 else       if (xidMap.containsKey(xid) == false) {
        xidMap.put(xid,null);
      }
    }
  }
);
  this.traversal(new VirtualLoggingListener(){
    public void recvOperation(    VirtualLoggingRecord action){
      Xid xid=action.getIdentifier();
      if (xidMap.containsKey(xid)) {
        this.execOperation(action);
      }
    }
    public void execOperation(    VirtualLoggingRecord action){
      Xid identifier=action.getIdentifier();
      TransactionXid xid=xidFactory.createGlobalXid(identifier.getGlobalTransactionId());
      Object obj=deserializer.deserialize(xid,action.getValue());
      if (TransactionArchive.class.isInstance(obj)) {
        TransactionArchive archive=(TransactionArchive)obj;
        xidMap.put(identifier,archive);
      }
 else       if (XAResourceArchive.class.isInstance(obj)) {
        TransactionArchive archive=xidMap.get(identifier);
        if (archive == null) {
          logger.error("Error occurred while recovering resource archive: {}",obj);
          return;
        }
        XAResourceArchive resourceArchive=(XAResourceArchive)obj;
        boolean matched=false;
        List<XAResourceArchive> remoteResources=archive.getRemoteResources();
        for (int i=0; matched == false && remoteResources != null && i < remoteResources.size(); i++) {
          XAResourceArchive element=remoteResources.get(i);
          if (resourceArchive.getXid().equals(element.getXid())) {
            matched=true;
            remoteResources.set(i,resourceArchive);
          }
        }
        if (matched == false) {
          remoteResources.add(resourceArchive);
        }
      }
 else       if (CompensableArchive.class.isInstance(obj)) {
        TransactionArchive archive=xidMap.get(identifier);
        if (archive == null) {
          logger.error("Error occurred while recovering compensable archive: {}",obj);
          return;
        }
        List<CompensableArchive> compensables=archive.getCompensableResourceList();
        CompensableArchive resourceArchive=(CompensableArchive)obj;
        boolean matched=false;
        for (int i=0; matched == false && compensables != null && i < compensables.size(); i++) {
          CompensableArchive element=compensables.get(i);
          if (resourceArchive.getIdentifier().equals(element.getIdentifier())) {
            matched=true;
            compensables.set(i,resourceArchive);
          }
        }
        if (matched == false) {
          compensables.add(resourceArchive);
        }
      }
    }
  }
);
  for (Iterator<Map.Entry<Xid,TransactionArchive>> itr=xidMap.entrySet().iterator(); itr.hasNext(); ) {
    Map.Entry<Xid,TransactionArchive> entry=itr.next();
    TransactionArchive archive=entry.getValue();
    if (archive == null) {
      continue;
    }
 else {
      try {
        callback.recover(archive);
      }
 catch (      RuntimeException rex) {
        logger.error("Error occurred while recovering transaction(xid= {}).",archive.getXid(),rex);
      }
    }
  }
}
