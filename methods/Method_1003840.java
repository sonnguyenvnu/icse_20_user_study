@Override public Operation save(){
  return Operation.of(() -> {
    if (state != State.NOT_LOADED) {
      ByteBuf serialized=serialize();
      storeAdapter.store(sessionId.getValue(),serialized).wiretap(o -> serialized.release()).then(() -> state=State.CLEAN);
    }
  }
);
}
