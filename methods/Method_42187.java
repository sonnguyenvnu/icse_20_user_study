private void toggleExplorerMode(boolean enabled){
  folders=new ArrayList<>();
  exploreMode=enabled;
  if (exploreMode) {
    displayContentFolder(Environment.getExternalStorageDirectory());
    imgExploreMode.setIcon(theme.getIcon(CommunityMaterial.Icon.cmd_folder));
    exploreModePanel.setVisibility(View.VISIBLE);
  }
 else {
    currentFolderPath.setText(R.string.local_folder);
    for (    String al : Hawk.get("albums",new ArrayList<String>()))     folders.add(new File(al));
    imgExploreMode.setIcon(theme.getIcon(CommunityMaterial.Icon.cmd_compass_outline));
    exploreModePanel.setVisibility(View.GONE);
  }
  adapter.notifyDataSetChanged();
}
