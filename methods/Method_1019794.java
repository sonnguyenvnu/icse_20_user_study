public void dispatch(com.sun.star.util.URL aURL,com.sun.star.beans.PropertyValue[] aArguments){
  if (aURL.Protocol.compareTo("fr.opensagres.xdocreport.xdocreportdesigntool:") == 0) {
    if (aURL.Path.compareTo("XDocReport") == 0) {
      startNewThread(new Runnable(){
        public void run(){
          try {
            SettingsDialog settingsDialog=new SettingsDialog(null,true);
            settingsDialog.setVisible(true);
          }
 catch (          Exception e) {
            e.printStackTrace();
            ;
          }
        }
      }
);
      return;
    }
 else     if (aURL.Path.compareTo("ListExisting") == 0) {
      startNewThread(new Runnable(){
        public void run(){
          ShowDialogBox showDialogBox=new ShowDialogBox();
          System.out.println(showDialogBox);
        }
      }
);
      return;
    }
  }
}
