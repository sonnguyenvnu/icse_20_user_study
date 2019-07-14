@Override public void inject(final ActionRequest actionRequest,final Targets targets){
  final String body=actionRequest.readRequestBody();
  if (StringUtil.isEmpty(body)) {
    return;
  }
  targets.forEachTargetAndIn(this,(target,in) -> {
    if (in.type() == String.class) {
      target.writeValue(in,body,true);
    }
 else {
      final Object value=parseRequestBody(body,in.type());
      target.writeValue(in,value,true);
    }
  }
);
}
