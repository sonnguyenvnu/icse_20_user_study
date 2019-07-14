private String fetchId(final @Nullable Bundle savedInstanceState){
  return savedInstanceState != null ? savedInstanceState.getString(VIEW_MODEL_ID_KEY) : UUID.randomUUID().toString();
}
