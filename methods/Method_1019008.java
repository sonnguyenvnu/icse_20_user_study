/** 
 * Multi-threaded invoke for event.
 * @param inst Instrumented runtime.
 */
public static void call(Instrumentation inst){
  Threads.runLaterFx(0,() -> {
    try {
      Bus.post(new NewInputEvent(inst));
    }
 catch (    Exception e) {
      Logging.error(e);
    }
  }
);
}
