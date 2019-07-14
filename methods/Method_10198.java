private static void postData() throws IOException, InterruptedException {
  var request=HttpRequest.newBuilder().uri(URI.create("https://postman-echo.com/post")).timeout(Duration.ofSeconds(30)).version(HttpClient.Version.HTTP_2).header("Content-Type","text/plain").POST(HttpRequest.BodyPublishers.ofString("Hi there!")).build();
  var client=HttpClient.newHttpClient();
  var response=client.send(request,HttpResponse.BodyHandlers.ofString());
  System.out.println(response.statusCode());
}
