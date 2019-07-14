/** 
 * Scan a folder recursively, and add any sketches found to the menu specified. Set the openReplaces parameter to true when opening the sketch should replace the sketch in the current window, or false when the sketch should open in a new window.
 */
protected boolean addSketches(JMenu menu,File folder,final boolean replaceExisting) throws IOException {
  if (!folder.isDirectory()) {
    return false;
  }
  if (folder.getName().equals("libraries")) {
    return false;
  }
  if (folder.getName().equals("sdk")) {
    File suspectSDKPath=new File(folder.getParent(),folder.getName());
    File expectedSDKPath=new File(sketchbookFolder,"android" + File.separator + "sdk");
    if (expectedSDKPath.getAbsolutePath().equals(suspectSDKPath.getAbsolutePath())) {
      return false;
    }
  }
  String[] list=folder.list();
  if (list == null) {
    return false;
  }
  Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
  ActionListener listener=new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      String path=e.getActionCommand();
      if (new File(path).exists()) {
        boolean replace=replaceExisting;
        if ((e.getModifiers() & ActionEvent.SHIFT_MASK) != 0) {
          replace=!replace;
        }
        handleOpen(path);
      }
 else {
        Messages.showWarning("Sketch Disappeared","The selected sketch no longer exists.\n" + "You may need to restart Processing to update\n" + "the sketchbook menu.",null);
      }
    }
  }
;
  boolean found=false;
  for (  String name : list) {
    if (name.charAt(0) == '.') {
      continue;
    }
    File subfolder=new File(folder,name);
    if (subfolder.isDirectory()) {
      File entry=checkSketchFolder(subfolder,name);
      if (entry != null) {
        JMenuItem item=new JMenuItem(name);
        item.addActionListener(listener);
        item.setActionCommand(entry.getAbsolutePath());
        menu.add(item);
        found=true;
      }
 else {
        JMenu submenu=new JMenu(name);
        boolean anything=addSketches(submenu,subfolder,replaceExisting);
        if (anything && !name.equals("old")) {
          menu.add(submenu);
          found=true;
        }
      }
    }
  }
  return found;
}
