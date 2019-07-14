/** 
 * {@link #setFilterDuplicates(boolean)} is disabled in each EpoxyController by default. It can betoggled individually in each controller, or alternatively you can use this to change the default value for all EpoxyControllers.
 */
public static void setGlobalDuplicateFilteringDefault(boolean filterDuplicatesByDefault){
  EpoxyController.filterDuplicatesDefault=filterDuplicatesByDefault;
}
