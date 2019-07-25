private void tick(){
  SwingUtilities.invokeLater(new Runnable(){
    public void run(){
      try {
        final boolean changed=refreshDir();
        if (changed) {
          jList1.setListData(new Vector<SimpleLine2>(currentDirectoryListing2));
          jList1.setVisible(true);
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
catch (      InterruptedException e) {
        e.printStackTrace();
      }
catch (      ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
);
}
