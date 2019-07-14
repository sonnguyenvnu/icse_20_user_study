private void buildOperationMenu(JMenu operationMenu){
  operationMenu.removeAll();
  packageExplorerStyle=new JCheckBoxMenuItem("Package Explorer Style");
  packageExplorerStyle.setSelected(luytenPrefs.isPackageExplorerStyle());
  packageExplorerStyle.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      luytenPrefs.setPackageExplorerStyle(packageExplorerStyle.isSelected());
      mainWindow.onTreeSettingsChanged();
    }
  }
);
  operationMenu.add(packageExplorerStyle);
  filterOutInnerClassEntries=new JCheckBoxMenuItem("Filter Out Inner Class Entries");
  filterOutInnerClassEntries.setSelected(luytenPrefs.isFilterOutInnerClassEntries());
  filterOutInnerClassEntries.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      luytenPrefs.setFilterOutInnerClassEntries(filterOutInnerClassEntries.isSelected());
      mainWindow.onTreeSettingsChanged();
    }
  }
);
  operationMenu.add(filterOutInnerClassEntries);
  singleClickOpenEnabled=new JCheckBoxMenuItem("Single Click Open");
  singleClickOpenEnabled.setSelected(luytenPrefs.isSingleClickOpenEnabled());
  singleClickOpenEnabled.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      luytenPrefs.setSingleClickOpenEnabled(singleClickOpenEnabled.isSelected());
    }
  }
);
  operationMenu.add(singleClickOpenEnabled);
  exitByEscEnabled=new JCheckBoxMenuItem("Exit By Esc");
  exitByEscEnabled.setSelected(luytenPrefs.isExitByEscEnabled());
  exitByEscEnabled.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      luytenPrefs.setExitByEscEnabled(exitByEscEnabled.isSelected());
    }
  }
);
  operationMenu.add(exitByEscEnabled);
}
