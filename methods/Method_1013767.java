@SuppressWarnings({"unchecked","rawtypes"}) public static <T>ByteBuf parse(T o){
  for (  RedisClientProtocol parser : parsers) {
    if (parser.supportes(o.getClass())) {
      Constructor constructor=getConstructor(parser.getClass(),o.getClass());
      if (constructor == null) {
        logger.warn("[getOptionParser][support argument, but can not find constructor]{},{}",parser,o);
        continue;
      }
      RedisClientProtocol curParser;
      try {
        curParser=(RedisClientProtocol)constructor.newInstance(o);
        return curParser.format();
      }
 catch (      InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
        logger.error("[getOptionParser][invocate constructor error]" + parser + "," + o,e);
      }
    }
  }
  throw new IllegalStateException("unknown parser for o:" + o.getClass());
}
