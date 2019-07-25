/** 
 * Pushes the specified context to the thread-local stack. To pop the context from the stack, call {@link SafeCloseable#close()}, which can be done using a  {@code try-with-resources} block:<pre> {@code}try (PushHandle ignored = ctx.push(true))  ... } }</pre> <p>NOTE: This method is only useful when it is undesirable to invoke the callbacks, such as replacing the current context with another. Prefer  {@link #push()} otherwise.
 * @param runCallbacks if {@code true}, the callbacks added by  {@link #onEnter(Consumer)} and{@link #onExit(Consumer)} will be invoked when the context is pushed to andremoved from the thread-local stack respectively. If  {@code false}, no callbacks will be executed. NOTE: In case of re-entrance, the callbacks will never run.
 */
default SafeCloseable push(boolean runCallbacks){
  final RequestContext oldCtx=RequestContextThreadLocal.getAndSet(this);
  if (oldCtx == this) {
    return () -> {
    }
;
  }
  if (runCallbacks) {
    if (oldCtx != null) {
      oldCtx.invokeOnChildCallbacks(this);
      invokeOnEnterCallbacks();
      return () -> {
        invokeOnExitCallbacks();
        RequestContextThreadLocal.set(oldCtx);
      }
;
    }
 else {
      invokeOnEnterCallbacks();
      return () -> {
        invokeOnExitCallbacks();
        RequestContextThreadLocal.remove();
      }
;
    }
  }
 else {
    if (oldCtx != null) {
      return () -> RequestContextThreadLocal.set(oldCtx);
    }
 else {
      return RequestContextThreadLocal::remove;
    }
  }
}
