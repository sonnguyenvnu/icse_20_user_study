@Override protected void query(HttpServletRequest req,HttpServletResponse resp,BackupQuery query){
  final AsyncContext context=req.startAsync();
  final CompletableFuture<BackupMessage> future=messageService.findMessage(query);
  future.exceptionally(throwable -> null).thenAccept(message -> {
    response(resp,serializer.serialize(message));
    context.complete();
  }
);
}
