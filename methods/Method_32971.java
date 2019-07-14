private ChangeListener<String> setupSearchField(final JFXTreeTableView<TreeTableViewController.Person> tableView){
  return (o,oldVal,newVal) -> tableView.setPredicate(personProp -> {
    final Person person=personProp.getValue();
    return person.firstName.get().contains(newVal) || person.lastName.get().contains(newVal) || Integer.toString(person.age.get()).contains(newVal);
  }
);
}
