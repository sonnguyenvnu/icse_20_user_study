CompletableFuture<KeyValue<byte[],byte[]>> _XXXXX_(byte[] key){
  RangeOp<byte[],byte[]> op=store.getOpFactory().newRange(key,Options.get());
  return store.range(op).thenApplyAsync(kvs -> {
    try {
      if (kvs.count() <= 0) {
        return null;
      }
 else {
        return kvs.getKvsAndClear().get(0);
      }
    }
  finally {
      kvs.close();
    }
  }
).whenComplete((kv,cause) -> op.close());
}