private Timer createTimer(){
  final long start=System.currentTimeMillis();
  return new Timer(1000,new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      long now=System.currentTimeMillis();
      long elapsedMillis=now - start;
      long elapsedSeconds=elapsedMillis / 1000;
      long minutes=(long)Math.floor(elapsedSeconds / 60);
      long seconds=elapsedSeconds - (minutes * 60);
      timeField.setText(formatTime(minutes,seconds));
    }
  }
);
}
