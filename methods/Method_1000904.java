@Override public MixInHandler snapshot(){
  MixInResolver overrides=Snapshottable.takeSnapshot(_overrides);
  Map<ClassKey,Class<?>> mixIns=(_localMixIns == null) ? null : new HashMap<ClassKey,Class<?>>(_localMixIns);
  return new MixInHandler(overrides,mixIns);
}
