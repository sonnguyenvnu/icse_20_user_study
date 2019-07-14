private void updateAdapterFilter(){
  adapter.setFilter(new HabitMatcherBuilder().setArchivedAllowed(showArchived).setCompletedAllowed(showCompleted).build());
  adapter.refresh();
}
