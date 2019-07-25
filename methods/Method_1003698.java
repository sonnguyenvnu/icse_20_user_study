public static RatpackServer capture(Block bootstrap) throws Exception {
  try {
    bootstrap.execute();
    return SERVER_HOLDER.get();
  }
  finally {
    SERVER_HOLDER.remove();
  }
}
