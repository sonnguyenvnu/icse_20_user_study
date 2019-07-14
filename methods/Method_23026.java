void requestContentHeight(final int width,final URL url){
  new Thread(new Runnable(){
    public void run(){
      final JEditorPane dummy=new JEditorPane();
      dummy.addPropertyChangeListener("page",new PropertyChangeListener(){
        @Override public void propertyChange(        PropertyChangeEvent evt){
          int high=dummy.getPreferredSize().height;
          editorPane.setPreferredSize(new Dimension(width,high));
          pack();
          setLocationRelativeTo(null);
          ready=true;
        }
      }
);
      try {
        dummy.setPage(url);
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
      dummy.setSize(width,Short.MAX_VALUE);
    }
  }
).start();
}
