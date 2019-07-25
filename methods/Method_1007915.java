/** 
 * Starts a child stop watch and stops any previously started time instruments.
 */
public void start(String name){
  stopLastTimeInstrument();
  StopWatch childSW=new StopWatch(name);
  childTimeInstrumentList.add(childSW);
}
