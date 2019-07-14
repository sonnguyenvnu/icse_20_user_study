@Override protected void configureSchema(){
  addMutationList(serviceToFields(FirestoreClient.class,ImmutableList.of("createDocument","updateDocument","deleteDocument")));
  addQueryList(serviceToFields(FirestoreClient.class,ImmutableList.of("getDocument","listDocuments")));
}
