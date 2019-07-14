/** 
 * When a Preference (that requires an independent fragment) is selected this is called.
 * @param item the Preference in question
 */
public void selectItem(int item){
  selectedItem=item;
switch (item) {
case START_PREFERENCE:
    loadPrefFragment(new PrefFrag(),R.string.setting);
  break;
case COLORS_PREFERENCE:
loadPrefFragment(new ColorPref(),R.string.color_title);
break;
case FOLDERS_PREFERENCE:
loadPrefFragment(new FoldersPref(),R.string.sidebarfolders_title);
break;
case QUICKACCESS_PREFERENCE:
loadPrefFragment(new QuickAccessPref(),R.string.sidebarquickaccess_title);
break;
case ADVANCEDSEARCH_PREFERENCE:
loadPrefFragment(new AdvancedSearchPref(),R.string.advanced_search);
break;
}
}
