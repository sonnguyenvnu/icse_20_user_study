@Override public LoggerDefine parse(MethodInterceptorHolder holder){
  AccessLogger methodAnn=holder.findMethodAnnotation(AccessLogger.class);
  AccessLogger classAnn=holder.findClassAnnotation(AccessLogger.class);
  String action=Stream.of(classAnn,methodAnn).filter(Objects::nonNull).map(AccessLogger::value).reduce((c,m) -> c.concat("-").concat(m)).orElse("");
  String describe=Stream.of(classAnn,methodAnn).filter(Objects::nonNull).map(AccessLogger::describe).flatMap(Stream::of).reduce((c,s) -> c.concat("\n").concat(s)).orElse("");
  return new LoggerDefine(action,describe);
}
