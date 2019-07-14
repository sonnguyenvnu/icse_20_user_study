public void addFilter(NotificationFilter filter){
  LOGGER.debug("Added filter '{}'",filter);
  filters.put(filter.getId(),filter);
}
