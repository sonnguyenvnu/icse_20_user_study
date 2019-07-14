/** 
 * Convert JanusGraph internal Mutation representation into HBase native commands.
 * @param mutations    Mutations to convert into HBase commands.
 * @param putTimestamp The timestamp to use for Put commands.
 * @param delTimestamp The timestamp to use for Delete commands.
 * @return Commands sorted by key converted from JanusGraph internal representation.
 * @throws org.janusgraph.diskstorage.PermanentBackendException
 */
@VisibleForTesting Map<StaticBuffer,Pair<List<Put>,Delete>> convertToCommands(Map<String,Map<StaticBuffer,KCVMutation>> mutations,final long putTimestamp,final long delTimestamp) throws PermanentBackendException {
  final Map<StaticBuffer,Pair<List<Put>,Delete>> commandsPerKey=new HashMap<>();
  for (  Map.Entry<String,Map<StaticBuffer,KCVMutation>> entry : mutations.entrySet()) {
    String cfString=getCfNameForStoreName(entry.getKey());
    byte[] cfName=Bytes.toBytes(cfString);
    for (    Map.Entry<StaticBuffer,KCVMutation> m : entry.getValue().entrySet()) {
      final byte[] key=m.getKey().as(StaticBuffer.ARRAY_FACTORY);
      KCVMutation mutation=m.getValue();
      Pair<List<Put>,Delete> commands=commandsPerKey.get(m.getKey());
      if (commands == null) {
        commands=new Pair<>();
        final List<Put> putList=new ArrayList<>();
        commands.setFirst(putList);
        commandsPerKey.put(m.getKey(),commands);
      }
      if (mutation.hasDeletions()) {
        if (commands.getSecond() == null) {
          Delete d=new Delete(key);
          compat.setTimestamp(d,delTimestamp);
          commands.setSecond(d);
        }
        for (        StaticBuffer b : mutation.getDeletions()) {
          commands.getSecond().addColumns(cfName,b.as(StaticBuffer.ARRAY_FACTORY),delTimestamp);
        }
      }
      if (mutation.hasAdditions()) {
        final Put putColumnsWithoutTtl=new Put(key,putTimestamp);
        for (        Entry e : mutation.getAdditions()) {
          final Integer ttl=(Integer)e.getMetaData().get(EntryMetaData.TTL);
          if (null != ttl && ttl > 0) {
            Put putColumnWithTtl=new Put(key,putTimestamp);
            addColumnToPut(putColumnWithTtl,cfName,putTimestamp,e);
            (putColumnWithTtl).setTTL(TimeUnit.SECONDS.toMillis((long)ttl));
            commands.getFirst().add(putColumnWithTtl);
          }
 else {
            addColumnToPut(putColumnsWithoutTtl,cfName,putTimestamp,e);
          }
        }
        if (!putColumnsWithoutTtl.isEmpty()) {
          commands.getFirst().add(putColumnsWithoutTtl);
        }
      }
    }
  }
  return commandsPerKey;
}
