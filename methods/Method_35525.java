public static GetServeEventsResult requestJournalEnabled(Paginator<ServeEvent> paginator){
  return new GetServeEventsResult(paginator,false);
}
