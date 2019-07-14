/** 
 * ?????code? eventType ????????(eventType)? ???? ?????code?0?class?voidMessage????????????code?0???voidMessage?
 * @param code      ??code
 * @param eventType ????
 * @param < T >
 * @return
 */
public <T>Observable<T> toObservable(final int code,final Class<T> eventType){
  return _bus.ofType(RxBusBaseMessage.class).filter(new Predicate<RxBusBaseMessage>(){
    @Override public boolean test(    RxBusBaseMessage rxBusBaseMessage) throws Exception {
      return rxBusBaseMessage.getCode() == code && eventType.isInstance(rxBusBaseMessage.getObject());
    }
  }
).map(new Function<RxBusBaseMessage,Object>(){
    @Override public Object apply(    RxBusBaseMessage rxBusBaseMessage) throws Exception {
      return rxBusBaseMessage.getObject();
    }
  }
).cast(eventType);
}
