/** 
 * Specify the  {@link ExceptionHandler} to use with the event handler.
 * @param exceptionHandler the exception handler to use.
 */
public void with(ExceptionHandler<? super T> exceptionHandler){
  ((BatchEventProcessor<T>)consumerRepository.getEventProcessorFor(eventHandler)).setExceptionHandler(exceptionHandler);
  consumerRepository.getBarrierFor(eventHandler).alert();
}
