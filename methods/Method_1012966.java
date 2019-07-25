/** 
 * @param code
 * @param event
 */
public void post(int code,Object event){
  Message msg=new Message(code,event);
  mEventBus.onNext(msg);
}
