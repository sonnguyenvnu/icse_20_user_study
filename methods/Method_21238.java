/** 
 * Binds project notifications to the adapter.
 */
public void projectNotifications(final @NonNull List<ProjectNotification> projectNotifications){
  clearSections();
  addSection(projectNotifications);
  notifyDataSetChanged();
}
