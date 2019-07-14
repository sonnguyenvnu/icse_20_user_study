/** 
 * This is a hack, each PreferenceFragment has a ListView that loses it's state (specifically the scrolled position) when the user accesses another PreferenceFragment. To prevent this, the Activity saves the ListView's state, so that it can be restored when the user returns to the PreferenceFragment. We cannot use the normal save/restore state functions because they only get called when the OS kills the fragment, not the user. See https://stackoverflow.com/a/12793395/3124150 for a better explanation. We cannot save the Parcelable in the fragment because the fragment is destroyed.
 */
public void saveListViewState(int prefFragment,Parcelable listViewState){
  fragmentsListViewParcelables[prefFragment]=listViewState;
}
