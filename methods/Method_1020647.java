/** 
 * ????
 * @param mobileMsgTemplate ??
 */
@Override public void check(MobileMsgTemplate mobileMsgTemplate){
  Assert.isBlank(mobileMsgTemplate.getMobile(),"???????");
  Assert.isBlank(mobileMsgTemplate.getContext(),"????????");
}
