public void saveAllDecompiled(final File inFile,final File outFile){
  new Thread(new Runnable(){
    @Override public void run(){
      long time=System.currentTimeMillis();
      try {
        bar.setVisible(true);
        setExtracting(true);
        label.setText("Extracting: " + outFile.getName());
        System.out.println("[SaveAll]: " + inFile.getName() + " -> " + outFile.getName());
        String inFileName=inFile.getName().toLowerCase();
        if (inFileName.endsWith(".jar") || inFileName.endsWith(".zip")) {
          doSaveJarDecompiled(inFile,outFile);
        }
 else         if (inFileName.endsWith(".class")) {
          doSaveClassDecompiled(inFile,outFile);
        }
 else {
          doSaveUnknownFile(inFile,outFile);
        }
        if (cancel) {
          label.setText("Cancelled");
          outFile.delete();
          setCancel(false);
        }
 else {
          label.setText("Completed: " + getTime(time));
        }
      }
 catch (      Exception e1) {
        label.setText("Cannot save file: " + outFile.getName());
        Luyten.showExceptionDialog("Unable to save file!\n",e1);
      }
 finally {
        setExtracting(false);
        bar.setVisible(false);
      }
    }
  }
).start();
}
