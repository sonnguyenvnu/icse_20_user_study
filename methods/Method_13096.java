public void updateRecentFilesMenu(List<File> files){
  invokeLater(() -> {
    recentFiles.removeAll();
    for (    File file : files) {
      JMenuItem menuItem=new JMenuItem(reduceRecentFilePath(file.getAbsolutePath()));
      menuItem.addActionListener(e -> openFilesCallback.accept(file));
      recentFiles.add(menuItem);
    }
  }
);
}
