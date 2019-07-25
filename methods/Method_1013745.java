@Override protected RedisInfo format(Object payload){
  String info=payloadToString(payload);
  return formatInfoString(info);
}
