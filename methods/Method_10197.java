private static void asyncRequest(){
  var request=HttpRequest.newBuilder().uri(URI.create("https://winterbe.com")).build();
  var client=HttpClient.newHttpClient();
  client.sendAsync(request,HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println);
}
