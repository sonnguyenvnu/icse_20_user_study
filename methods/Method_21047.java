/** 
 * Determines the correct string to display for a filter depending on where it is shown.
 * @param context           context
 * @param ksString          ksString for string formatting
 * @param isToolbar         true if string is being displayed in the {@link com.kickstarter.ui.toolbars.DiscoveryToolbar}
 * @param isParentFilter    true if string is being displayed as a {@link com.kickstarter.ui.viewholders.discoverydrawer.ParentFilterViewHolder}
 * @return the appropriate filter string
 */
public @NonNull String filterString(final @NonNull Context context,final @NonNull KSString ksString,final boolean isToolbar,final boolean isParentFilter){
  if (isTrue(staffPicks())) {
    return context.getString(R.string.Projects_We_Love);
  }
 else   if (starred() != null && starred() == 1) {
    return context.getString(R.string.Saved);
  }
 else   if (backed() != null && backed() == 1) {
    return context.getString(R.string.discovery_backing);
  }
 else   if (social() != null && social() == 1) {
    return isToolbar ? context.getString(R.string.Following) : context.getString(R.string.Backed_by_people_you_follow);
  }
 else   if (category() != null) {
    return category().isRoot() && !isParentFilter && !isToolbar ? ksString.format(context.getString(R.string.All_category_name_Projects),"category_name",category().name()) : category().name();
  }
 else   if (location() != null) {
    return location().displayableName();
  }
 else   if (isTrue(recommended())) {
    return isToolbar ? context.getString(R.string.Recommended) : context.getString(R.string.discovery_recommended_for_you);
  }
 else {
    return context.getString(R.string.All_Projects);
  }
}
