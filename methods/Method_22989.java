static private void updateMenuRecord(JMenu menu,final Record rec,String sketchbookPath){
  try {
    String recPath=new File(rec.getPath()).getParent();
    String purtyPath=null;
    if (recPath.startsWith(sketchbookPath)) {
      purtyPath="sketchbook \u2192 " + recPath.substring(sketchbookPath.length() + 1);
    }
 else {
      List<Mode> modes=base.getModeList();
      for (      Mode mode : modes) {
        File examplesFolder=mode.getExamplesFolder();
        String examplesPath=examplesFolder.getAbsolutePath();
        if (recPath.startsWith(examplesPath)) {
          String modePrefix=mode.getTitle() + " ";
          if (mode.getTitle().equals("Standard")) {
            modePrefix="";
          }
          purtyPath=modePrefix + "examples \u2192 " + recPath.substring(examplesPath.length() + 1);
          break;
        }
        if (mode.coreLibraries != null) {
          for (          Library lib : mode.coreLibraries) {
            examplesFolder=lib.getExamplesFolder();
            examplesPath=examplesFolder.getAbsolutePath();
            if (recPath.startsWith(examplesPath)) {
              purtyPath=lib.getName() + " examples \u2192 " + recPath.substring(examplesPath.length() + 1);
              break;
            }
          }
        }
        if (mode.contribLibraries != null) {
          for (          Library lib : mode.contribLibraries) {
            examplesFolder=lib.getExamplesFolder();
            examplesPath=examplesFolder.getAbsolutePath();
            if (recPath.startsWith(examplesPath)) {
              purtyPath=lib.getName() + " examples \u2192 " + recPath.substring(examplesPath.length() + 1);
              break;
            }
          }
        }
      }
    }
    if (purtyPath == null) {
      String homePath=System.getProperty("user.home");
      if (recPath.startsWith(homePath)) {
        String desktopPath=homePath + File.separator + "Desktop";
        if (recPath.startsWith(desktopPath)) {
          purtyPath="Desktop \u2192 " + recPath.substring(desktopPath.length() + 1);
        }
 else {
          String userName=new File(homePath).getName();
          purtyPath=userName + " \u2192 " + recPath.substring(homePath.length() + 1);
        }
      }
 else {
        purtyPath=recPath;
      }
    }
    JMenuItem item=new JMenuItem(purtyPath);
    item.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        base.handleOpen(rec.path);
      }
    }
);
    menu.insert(item,0);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
