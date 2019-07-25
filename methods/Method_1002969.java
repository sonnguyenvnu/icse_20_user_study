private static void respond(SerializationFormat serializationFormat,HttpData content,CompletableFuture<HttpResponse> res){
  res.complete(HttpResponse.of(HttpStatus.OK,serializationFormat.mediaType(),content));
}
