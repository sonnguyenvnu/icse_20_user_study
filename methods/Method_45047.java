public void loadTree(){
  new Thread(new Runnable(){
    @Override public void run(){
      try {
        if (file == null) {
          return;
        }
        tree.setModel(new DefaultTreeModel(null));
        if (file.length() > MAX_JAR_FILE_SIZE_BYTES) {
          throw new TooLargeFileException(file.length());
        }
        if (file.getName().endsWith(".zip") || file.getName().endsWith(".jar")) {
          JarFile jfile;
          jfile=new JarFile(file);
          getLabel().setText("Loading: " + jfile.getName());
          bar.setVisible(true);
          JarEntryFilter jarEntryFilter=new JarEntryFilter(jfile);
          List<String> mass=null;
          if (luytenPrefs.isFilterOutInnerClassEntries()) {
            mass=jarEntryFilter.getEntriesWithoutInnerClasses();
          }
 else {
            mass=jarEntryFilter.getAllEntriesFromJar();
          }
          buildTreeFromMass(mass);
          if (state == null) {
            ITypeLoader jarLoader=new JarTypeLoader(jfile);
            typeLoader.getTypeLoaders().add(jarLoader);
            state=new State(file.getCanonicalPath(),file,jfile,jarLoader);
          }
          open=true;
          getLabel().setText("Complete");
        }
 else {
          TreeNodeUserObject topNodeUserObject=new TreeNodeUserObject(getName(file.getName()));
          final DefaultMutableTreeNode top=new DefaultMutableTreeNode(topNodeUserObject);
          tree.setModel(new DefaultTreeModel(top));
          settings.setTypeLoader(new InputTypeLoader());
          open=true;
          getLabel().setText("Complete");
          new Thread(){
            public void run(){
              TreePath trp=new TreePath(top.getPath());
              openEntryByTreePath(trp);
            }
          }
.start();
        }
        if (treeExpansionState != null) {
          try {
            TreeUtil treeUtil=new TreeUtil(tree);
            treeUtil.restoreExpanstionState(treeExpansionState);
          }
 catch (          Exception e) {
            Luyten.showExceptionDialog("Exception!",e);
          }
        }
      }
 catch (      TooLargeFileException e) {
        getLabel().setText("File is too large: " + file.getName() + " - size: " + e.getReadableFileSize());
        closeFile();
      }
catch (      Exception e1) {
        Luyten.showExceptionDialog("Cannot open " + file.getName() + "!",e1);
        getLabel().setText("Cannot open: " + file.getName());
        closeFile();
      }
 finally {
        mainWindow.onFileLoadEnded(file,open);
        bar.setVisible(false);
      }
    }
  }
).start();
}
