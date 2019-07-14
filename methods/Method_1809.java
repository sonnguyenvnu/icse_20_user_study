public String toHttpRangeHeaderValue(){
  return String.format((Locale)null,"bytes=%s-%s",valueOrEmpty(from),valueOrEmpty(to));
}
