@Override public void append(final String msg){
  SwingUtilities.invokeLater(new Runnable(){
    @Override public void run(){
      tt.append(msg);
    }
  }
);
}
