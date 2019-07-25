public synchronized void update(BlockCapsule blockCapsule){
  List<ByteString> witnesses=manager.getWitnessController().getActiveWitnesses();
  ByteString witness=blockCapsule.getWitnessAddress();
  int slot=witnesses.indexOf(witness);
  if (slot < 0) {
    return;
  }
  int version=blockCapsule.getInstance().getBlockHeader().getRawData().getVersion();
  if (version < ForkBlockVersionConsts.ENERGY_LIMIT) {
    return;
  }
  downgrade(version,slot);
  byte[] stats=manager.getDynamicPropertiesStore().statsByVersion(version);
  if (check(stats)) {
    upgrade(version,stats.length);
    return;
  }
  if (Objects.isNull(stats) || stats.length != witnesses.size()) {
    stats=new byte[witnesses.size()];
  }
  stats[slot]=VERSION_UPGRADE;
  manager.getDynamicPropertiesStore().statsByVersion(version,stats);
  logger.info("*******update hard fork:{}, witness size:{}, solt:{}, witness:{}, version:{}",Streams.zip(witnesses.stream(),Stream.of(ArrayUtils.toObject(stats)),Maps::immutableEntry).map(e -> Maps.immutableEntry(Wallet.encode58Check(e.getKey().toByteArray()),e.getValue())).map(e -> Maps.immutableEntry(StringUtils.substring(e.getKey(),e.getKey().length() - 4),e.getValue())).collect(Collectors.toList()),witnesses.size(),slot,Wallet.encode58Check(witness.toByteArray()),version);
}
