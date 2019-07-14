public static GetServeEventsResult requestJournalDisabled(Paginator<ServeEvent> paginator){
  return new GetServeEventsResult(paginator,true);
}
