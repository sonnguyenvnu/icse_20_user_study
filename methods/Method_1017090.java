public static RequestTimer<WriteSuggest> timer(){
  return new RequestTimer<>(WriteSuggest::of);
}
