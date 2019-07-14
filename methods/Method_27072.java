@NonNull public static Single<List<Event>> getEvents(@NonNull String login){
  return RxHelper.getSingle(App.getInstance().getDataStore().select(Event.class).where(Event.LOGIN.eq(login)).orderBy(Event.CREATED_AT.desc()).get().observable().toList());
}
