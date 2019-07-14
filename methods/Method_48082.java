@Override public Map<String,String> getCompressionOptions(String cf) throws BackendException {
  CTConnection conn=null;
  Map<String,String> result=null;
  try {
    conn=pool.borrowObject(keySpaceName);
    Cassandra.Client client=conn.getClient();
    KsDef ksDef=client.describe_keyspace(keySpaceName);
    for (    CfDef cfDef : ksDef.getCf_defs()) {
      if (null != cfDef && cfDef.getName().equals(cf)) {
        result=cfDef.getCompression_options();
        break;
      }
    }
    return result;
  }
 catch (  InvalidRequestException e) {
    log.debug("Keyspace {} does not exist",keySpaceName);
    return null;
  }
catch (  Exception e) {
    throw new TemporaryBackendException(e);
  }
 finally {
    pool.returnObjectUnsafe(keySpaceName,conn);
  }
}
