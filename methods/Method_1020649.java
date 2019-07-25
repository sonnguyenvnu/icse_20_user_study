/** 
 * ????
 * @param mobileMsgTemplate ??
 */
@Override public void fail(MobileMsgTemplate mobileMsgTemplate){
  log.error("?????? -> ???{} -> ????{}",mobileMsgTemplate.getType(),mobileMsgTemplate.getMobile());
}
