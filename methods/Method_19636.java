private static List getStoriesContent(){
  final List<String> contents=new ArrayList<>();
  for (int i=0; i < 5; i++) {
    contents.add("StoryCard #" + i + ": This is some test content. It should fill at least " + "one line. This is a story card. You can interact with the menu button " + "and save button.");
  }
  return contents;
}
