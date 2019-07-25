@Get("/welcome") public HttpResponse welcome(Cookies cookies){
  final String name=cookies.stream().filter(c -> "username".equals(c.name())).map(Cookie::value).findFirst().orElseThrow(() -> new IllegalArgumentException("No username is found."));
  return HttpResponse.of(HttpStatus.OK,MediaType.HTML_UTF_8,"<html><body>Hello, %s! You can see this message " + "because you've been authenticated by SSOCircle.</body></html>",name);
}
