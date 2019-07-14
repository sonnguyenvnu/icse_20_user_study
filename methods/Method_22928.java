/** 
 * Start the timer that handles flushing the console text. Has to be started and stopped/cleared because the Timer thread will keep a reference to its Editor around even after the Editor has been closed, leaking memory.
 */
protected void startTimer(){
  if (flushTimer == null) {
    flushTimer=new Timer(250,new ActionListener(){
      public void actionPerformed(      ActionEvent evt){
        flush();
      }
    }
);
    flushTimer.start();
  }
}
