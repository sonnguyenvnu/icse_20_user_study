@Override public AsyncFuture<Void> evaluate(final List<String> commands,final ShellIO io){
  return async.call(() -> {
    final AtomicBoolean running=new AtomicBoolean(true);
    final AtomicInteger fileCounter=new AtomicInteger();
    final Map<Integer,InputStream> reading=new HashMap<>();
    final Map<Integer,OutputStream> writing=new HashMap<>();
    final Map<Integer,Callable<Void>> closers=new HashMap<>();
    try (final ShellConnection c=connect()){
      c.send(MessageBuilder.evaluateRequest(commands));
      final SimpleMessageVisitor<Optional<Message>> visitor=setupVisitor(io,running,fileCounter,reading,writing,closers);
      while (true) {
        final Optional<Message> out=c.receiveAndParse(visitor);
        if (!running.get()) {
          break;
        }
        if (out.isPresent()) {
          final Message o=out.get();
          try {
            c.send(o);
          }
 catch (          Exception e) {
            throw new Exception("Failed to send response: " + o,e);
          }
        }
      }
    }
     return null;
  }
);
}
