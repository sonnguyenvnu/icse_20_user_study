/** 
 * ??Tracing??
 * @param tracingSetter Tracing?????
 */
public static void transmit(TracingSetter tracingSetter){
  if (TracingContext.tracing().hasGroup()) {
    log.debug("tracing transmit group:{}",TracingContext.tracing().groupId());
    tracingSetter.set(TracingConstants.HEADER_KEY_GROUP_ID,TracingContext.tracing().groupId());
    tracingSetter.set(TracingConstants.HEADER_KEY_APP_MAP,Base64Utils.encodeToString(TracingContext.tracing().appMapString().getBytes(StandardCharsets.UTF_8)));
  }
}
