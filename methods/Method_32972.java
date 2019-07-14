private ObservableList<Person> generateDummyData(final int numberOfEntries){
  final ObservableList<Person> dummyData=FXCollections.observableArrayList();
  for (int i=0; i < numberOfEntries; i++) {
    dummyData.add(createNewRandomPerson());
  }
  return dummyData;
}
