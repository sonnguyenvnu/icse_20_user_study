public Object down(Event evt){
switch (evt.getType()) {
case ExecutorEvent.TASK_SUBMIT:
    Runnable runnable=evt.getArg();
  long requestId=Math.abs(counter.getAndIncrement());
if (requestId == Long.MIN_VALUE) {
  counter.set(0);
  requestId=Math.abs(counter.getAndIncrement());
}
_requestId.put(runnable,requestId);
_awaitingConsumer.add(runnable);
sendToCoordinator(RUN_REQUEST,requestId,local_addr);
break;
case ExecutorEvent.CONSUMER_READY:
Thread currentThread=Thread.currentThread();
long threadId=currentThread.getId();
_consumerId.put(threadId,PRESENT);
try {
for (; ; ) {
CyclicBarrier barrier=new CyclicBarrier(2);
_taskBarriers.put(threadId,barrier);
sendToCoordinator(Type.CONSUMER_READY,threadId,local_addr);
try {
barrier.await();
break;
}
 catch (BrokenBarrierException e) {
if (log.isDebugEnabled()) log.debug("Producer timed out before we picked up" + " the task, have to tell coordinator" + " we are still good.");
}
}
runnable=_tasks.remove(threadId);
_runnableThreads.put(runnable,currentThread);
return runnable;
}
 catch (InterruptedException e) {
if (log.isDebugEnabled()) log.debug("Consumer " + threadId + " stopped via interrupt");
sendToCoordinator(Type.CONSUMER_UNREADY,threadId,local_addr);
Thread.currentThread().interrupt();
}
 finally {
_taskBarriers.remove(threadId);
_consumerId.remove(threadId);
}
break;
case ExecutorEvent.TASK_COMPLETE:
Object arg=evt.getArg();
Throwable throwable=null;
if (arg instanceof Object[]) {
Object[] array=(Object[])arg;
runnable=(Runnable)array[0];
throwable=(Throwable)array[1];
}
 else {
runnable=(Runnable)arg;
}
Owner owner=_running.remove(runnable);
_runnableThreads.remove(runnable);
Object value=null;
boolean exception=false;
if (throwable != null) {
if (throwable instanceof InterruptedException) {
if (log.isDebugEnabled()) log.debug("Run rejected due to interrupted exception returned");
sendRequest(owner.address,Type.RUN_REJECTED,owner.requestId,null);
break;
}
value=throwable;
exception=true;
}
 else if (runnable instanceof RunnableFuture<?>) {
RunnableFuture<?> future=(RunnableFuture<?>)runnable;
boolean interrupted=false;
boolean gotValue=false;
while (!gotValue) {
try {
value=future.get();
gotValue=true;
}
 catch (InterruptedException e) {
interrupted=true;
}
catch (ExecutionException e) {
value=e.getCause();
exception=true;
gotValue=true;
}
}
if (interrupted) {
Thread.currentThread().interrupt();
}
}
if (owner != null) {
final Type type;
final Object valueToSend;
if (value == null) {
type=Type.RESULT_SUCCESS;
valueToSend=value;
}
 else if (value instanceof Serializable || value instanceof Externalizable || value instanceof Streamable) {
type=exception ? Type.RESULT_EXCEPTION : Type.RESULT_SUCCESS;
valueToSend=value;
}
 else {
type=Type.RESULT_EXCEPTION;
valueToSend=new NotSerializableException(value.getClass().getName());
}
if (local_addr.equals(owner.getAddress())) {
if (log.isTraceEnabled()) log.trace("[redirect] <--> [" + local_addr + "] " + type.name() + " [" + value + (owner.requestId != -1 ? " request id: " + owner.requestId : "") + "]");
if (type == Type.RESULT_SUCCESS) {
handleValueResponse(local_addr,owner.requestId,valueToSend);
}
 else if (type == Type.RESULT_EXCEPTION) {
handleExceptionResponse(local_addr,owner.requestId,(Throwable)valueToSend);
}
}
 else {
sendRequest(owner.getAddress(),type,owner.requestId,valueToSend);
}
}
 else {
if (log.isTraceEnabled()) {
log.trace("Could not return result - most likely because it was interrupted");
}
}
break;
case ExecutorEvent.TASK_CANCEL:
Object[] array=evt.getArg();
runnable=(Runnable)array[0];
if (_awaitingConsumer.remove(runnable)) {
_requestId.remove(runnable);
ExecutorNotification notification=notifiers.remove(runnable);
if (notification != null) {
notification.interrupted(runnable);
}
if (log.isTraceEnabled()) log.trace("Cancelled task " + runnable + " before it was picked up");
return Boolean.TRUE;
}
 else if (array[1] == Boolean.TRUE) {
owner=removeKeyForValue(_awaitingReturn,runnable);
if (owner != null) {
Long requestIdValue=_requestId.remove(runnable);
if (requestIdValue != null) {
if (requestIdValue != owner.getRequestId()) {
log.warn("Cancelling requestId didn't match waiting");
}
sendRequest(owner.getAddress(),Type.INTERRUPT_RUN,owner.getRequestId(),null);
}
}
 else {
if (log.isTraceEnabled()) log.warn("Couldn't interrupt server task: " + runnable);
}
ExecutorNotification notification=notifiers.remove(runnable);
if (notification != null) {
notification.interrupted(runnable);
}
return Boolean.TRUE;
}
 else {
return Boolean.FALSE;
}
case ExecutorEvent.ALL_TASK_CANCEL:
array=evt.getArg();
@SuppressWarnings("unchecked") Set<Runnable> runnables=(Set<Runnable>)array[0];
Boolean booleanValue=(Boolean)array[1];
List<Runnable> notRan=new ArrayList<>();
for (Runnable cancelRunnable : runnables) {
if (!_awaitingConsumer.remove(cancelRunnable) && booleanValue == Boolean.TRUE) {
synchronized (_awaitingReturn) {
owner=removeKeyForValue(_awaitingReturn,cancelRunnable);
if (owner != null) {
Long requestIdValue=_requestId.remove(cancelRunnable);
if (requestIdValue != owner.getRequestId()) {
log.warn("Cancelling requestId didn't match waiting");
}
sendRequest(owner.getAddress(),Type.INTERRUPT_RUN,owner.getRequestId(),null);
}
ExecutorNotification notification=notifiers.remove(cancelRunnable);
if (notification != null) {
log.trace("Notifying listener");
notification.interrupted(cancelRunnable);
}
}
}
 else {
_requestId.remove(cancelRunnable);
notRan.add(cancelRunnable);
}
}
return notRan;
case Event.SET_LOCAL_ADDRESS:
local_addr=evt.getArg();
break;
case Event.VIEW_CHANGE:
handleView(evt.getArg());
break;
}
return down_prot.down(evt);
}
