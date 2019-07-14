private HttpHeaders withoutContentEncodingAndContentLength(LoggedResponse response){
  return new HttpHeaders(filter(response.getHeaders().all(),new Predicate<HttpHeader>(){
    public boolean apply(    HttpHeader header){
      return !EXCLUDED_HEADERS.contains(header.caseInsensitiveKey());
    }
  }
));
}
