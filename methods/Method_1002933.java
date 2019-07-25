void export(String mdcKey){
  requireNonNull(mdcKey,"mdcKey");
  final List<BuiltInProperty> builtInPropertyList=BuiltInProperty.findByMdcKeyPattern(mdcKey);
  if (!builtInPropertyList.isEmpty()) {
    builtIns.addAll(builtInPropertyList);
    return;
  }
  if (mdcKey.contains(BuiltInProperty.WILDCARD_STR)) {
    return;
  }
  if (mdcKey.startsWith(PREFIX_ATTRS)) {
    final String[] components=mdcKey.split(":");
switch (components.length) {
case 2:
      addAttribute(components[0].substring(PREFIX_ATTRS.length()),AttributeKey.valueOf(components[1]));
    break;
case 3:
  final Function<?,String> stringifier=newStringifier(mdcKey,components[2]);
addAttribute(components[0].substring(PREFIX_ATTRS.length()),AttributeKey.valueOf(components[1]),stringifier);
break;
default :
throw new IllegalArgumentException("invalid attribute export: " + mdcKey + " (expected: attrs.<alias>:<AttributeKey.name>[:<FQCN of Function<?, String>>])");
}
return;
}
if (mdcKey.startsWith(PREFIX_HTTP_REQ_HEADERS)) {
addHttpRequestHeader(mdcKey.substring(PREFIX_HTTP_REQ_HEADERS.length()));
return;
}
if (mdcKey.startsWith(PREFIX_HTTP_RES_HEADERS)) {
addHttpResponseHeader(mdcKey.substring(PREFIX_HTTP_RES_HEADERS.length()));
return;
}
throw new IllegalArgumentException("unknown MDC key: " + mdcKey);
}
