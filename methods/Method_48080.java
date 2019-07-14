private void ensureColumnFamilyExists(String ksName,String cfName) throws BackendException {
  CTConnection conn=null;
  try {
    KsDef keyspaceDef=ensureKeyspaceExists(ksName);
    conn=pool.borrowObject(ksName);
    Cassandra.Client client=conn.getClient();
    log.debug("Looking up metadata on keyspace {}...",ksName);
    boolean foundColumnFamily=false;
    for (    CfDef cfDef : keyspaceDef.getCf_defs()) {
      String curCfName=cfDef.getName();
      if (curCfName.equals(cfName))       foundColumnFamily=true;
    }
    if (!foundColumnFamily) {
      createColumnFamily(client,ksName,cfName,"org.apache.cassandra.db.marshal.BytesType");
    }
 else {
      log.debug("Keyspace {} and ColumnFamily {} were found.",ksName,cfName);
    }
  }
 catch (  SchemaDisagreementException e) {
    throw new TemporaryBackendException(e);
  }
catch (  Exception e) {
    throw new PermanentBackendException(e);
  }
 finally {
    pool.returnObjectUnsafe(ksName,conn);
  }
}
