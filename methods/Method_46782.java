/** 
 * Initializes an interactive shell, which will stay throughout the app lifecycle The shell is associated with a handler thread which maintain the message queue from the callbacks of shell as we certainly cannot allow the callbacks to run on same thread because of possible deadlock situation and the asynchronous behaviour of LibSuperSU
 */
private void initializeInteractiveShell(){
  if (isRootExplorer()) {
    handlerThread=new HandlerThread("handler");
    handlerThread.start();
    handler=new Handler(handlerThread.getLooper());
    shellInteractive=(new Shell.Builder()).useSU().setHandler(handler).open();
  }
}
