@NonNull private NavigationEntry getViewForSelection(@NavigationItem int navItem){
switch (navItem) {
case NAVIGATION_ITEM_ABOUT:
    return aboutEntry;
case NAVIGATION_ITEM_ALL_ALBUMS:
  return albumsEntry;
case NAVIGATION_ITEM_ALL_MEDIA:
return mediaEntry;
case NAVIGATION_ITEM_DONATE:
return donateEntry;
case NAVIGATION_ITEM_HIDDEN_FOLDERS:
return hiddenFoldersEntry;
case NAVIGATION_ITEM_SETTINGS:
return settingsEntry;
case NAVIGATION_ITEM_WALLPAPERS:
return wallpapersEntry;
case NAVIGATION_ITEM_TIMELINE:
return timelineEntry;
default :
return albumsEntry;
}
}
