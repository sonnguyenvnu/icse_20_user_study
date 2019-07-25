public void reset(MPSConfigurationBean data){
  if (myContentEntriesEditor != null) {
    Disposer.dispose(myContentEntriesEditor);
    myContentEntriesEditor=null;
  }
  myContentEntriesEditor=new ModelRootContentEntriesEditor(data.getSolutionDescriptor(),ProjectHelper.fromIdeaProject(myContext.getProject()));
  Disposer.register(myParentDisposable,myContentEntriesEditor);
  VirtualFile defaultFolder=myContext.getModule().getModuleFile() != null ? myContext.getModule().getModuleFile().getParent() : myContext.getProject().getBaseDir();
  myContentEntriesEditor.setDefaultFolder(VirtualFileUtils.toIFile(defaultFolder));
  myRootPanel.removeAll();
  myRootPanel.add(myContentEntriesEditor.getComponent(),BorderLayout.CENTER);
  for (  ModelRootEntryContainer container : myContentEntriesEditor.getModelRootsEntries()) {
    container.getModelRootEntry().addModelRootEntryListener(new ModelRootEntryListener(){
      @Override public void fireDataChanged(){
        final ModifiableRootModel modifiableRootModel=myContext.getModifiableRootModel();
        for (        ModelRoot path : myContentEntriesEditor.getModelRoots()) {
          if (path instanceof DefaultModelRoot) {
            for (            SourceRoot mpsSourceRoot : ((DefaultModelRoot)path).getSourceRoots(SourceRootKinds.SOURCES)) {
              VirtualFile mpsSourceRootVFile=VirtualFileUtils.getProjectVirtualFile(mpsSourceRoot.getAbsolutePath());
              if (mpsSourceRootVFile == null) {
                continue;
              }
              for (              ContentEntry contentEntry : modifiableRootModel.getContentEntries()) {
                VirtualFile contentRoot=contentEntry.getFile();
                if (contentRoot == null) {
                  continue;
                }
                if (!VfsUtilCore.isUnder(mpsSourceRootVFile,Collections.singleton(contentRoot))) {
                  continue;
                }
                Set<VirtualFile> ideaSourceFolders=new HashSet<>(Arrays.asList(contentEntry.getSourceFolderFiles()));
                if (VfsUtilCore.isUnder(mpsSourceRootVFile,ideaSourceFolders)) {
                  break;
                }
                contentEntry.addSourceFolder(mpsSourceRootVFile,false);
              }
            }
          }
        }
      }
    }
);
  }
}
