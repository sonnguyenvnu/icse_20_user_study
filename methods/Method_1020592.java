/** 
 * Stops the Recorder and writes its current tape out to a file.
 * @throws IllegalStateException if the Recorder is not started.
 */
public void stop(){
  if (tape == null) {
    throw new IllegalStateException("stop called when Recorder is not started");
  }
  for (  RecorderListener listener : listeners) {
    listener.onRecorderStop();
  }
  getTapeLoader().writeTape(tape);
  tape=null;
}
