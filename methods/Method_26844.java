@GetMapping("/uriComponentsBuilder") public String uriComponentsBuilder(){
  String date=this.conversionService.convert(new LocalDate(2011,12,31),String.class);
  UriComponents redirectUri=UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date",date).build().expand("a123").encode();
  return "redirect:" + redirectUri.toUriString();
}
