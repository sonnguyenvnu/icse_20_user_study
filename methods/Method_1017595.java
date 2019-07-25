@Override public StateMachineContext<S,E> read(Stat stat) throws Exception {
  return deserialize(curatorClient.getData().storingStatIn(stat).forPath(path));
}
