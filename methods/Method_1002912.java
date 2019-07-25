/** 
 * Returns the received  {@code x-armeria-text},  {@code x-armeria-sequence},  {@code Cookie} headersto the sender as a JSON list.
 */
@Get("/header") public HttpResponse header(@Header String xArmeriaText,@Header List<Integer> xArmeriaSequence,Cookies cookies) throws JsonProcessingException {
  return HttpResponse.of(HttpStatus.OK,MediaType.JSON_UTF_8,mapper.writeValueAsBytes(Arrays.asList(xArmeriaText,xArmeriaSequence,cookies.stream().map(Cookie::name).collect(Collectors.toList()))));
}
