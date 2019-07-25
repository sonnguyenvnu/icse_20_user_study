@Around("pointcut()") public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
  HttpServletRequest request=RequestHolder.getHttpServletRequest();
  MethodSignature signature=(MethodSignature)joinPoint.getSignature();
  Method signatureMethod=signature.getMethod();
  Limit limit=signatureMethod.getAnnotation(Limit.class);
  LimitType limitType=limit.limitType();
  String key=limit.key();
  if (StringUtils.isEmpty(key)) {
switch (limitType) {
case IP:
      key=StringUtils.getIP(request);
    break;
default :
  key=signatureMethod.getName();
}
}
ImmutableList keys=ImmutableList.of(StringUtils.join(limit.prefix(),"_",key,"_",request.getRequestURI().replaceAll("/","_")));
String luaScript=buildLuaScript();
RedisScript<Number> redisScript=new DefaultRedisScript<>(luaScript,Number.class);
Number count=(Number)redisTemplate.execute(redisScript,keys,limit.count(),limit.period());
if (null != count && count.intValue() <= limit.count()) {
logger.info("?{}???key? {}???? [{}] ???",count,keys,limit.name());
return joinPoint.proceed();
}
 else {
throw new BadRequestException("???????");
}
}
