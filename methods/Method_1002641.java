/** 
 * Adds the specified  {@link ClientOptionValue}.
 */
@SuppressWarnings({"unchecked","rawtypes"}) public <T>B option(ClientOptionValue<T> optionValue){
  requireNonNull(optionValue,"optionValue");
  final ClientOption<?> opt=optionValue.option();
  if (opt == ClientOption.DECORATION) {
    final ClientDecoration d=(ClientDecoration)optionValue.value();
    d.entries().forEach(e -> decoration.add0(e.requestType(),e.responseType(),(Function)e.decorator()));
  }
 else   if (opt == ClientOption.HTTP_HEADERS) {
    final HttpHeaders h=(HttpHeaders)optionValue.value();
    setHttpHeaders(h);
  }
 else {
    options.put(opt,optionValue);
  }
  return self();
}
