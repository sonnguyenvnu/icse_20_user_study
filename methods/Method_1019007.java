/** 
 * Multi-threaded invoke for event.
 * @param file File to load.
 */
public static void call(File file){
  Threads.runLaterFx(0,() -> {
    try {
      Bus.post(new NewInputEvent(file));
    }
 catch (    Exception e) {
      Logging.error(e);
    }
  }
);
}
