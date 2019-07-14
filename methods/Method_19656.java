@OnCreateLayout static Component onCreateLayout(ComponentContext c){
  return Column.create(c).backgroundRes(android.R.color.darker_gray).child(StoryCardComponent.create(c).content("This is some test content. It should fill at least one line. " + "This is a story card. You can interact with the menu button " + "and save button.")).build();
}
