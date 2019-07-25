private void say(String[] args,Console console){
  StringBuilder msg=new StringBuilder();
  for (  String word : args) {
    if (word.startsWith("%") && word.endsWith("%") && word.length() > 2) {
      String itemName=word.substring(1,word.length() - 1);
      try {
        Item item=this.itemRegistry.getItemByPattern(itemName);
        msg.append(item.getState().toString());
      }
 catch (      ItemNotFoundException e) {
        console.println("Error: Item '" + itemName + "' does not exist.");
      }
catch (      ItemNotUniqueException e) {
        console.print("Error: Multiple items match this pattern: ");
        for (        Item item : e.getMatchingItems()) {
          console.print(item.getName() + " ");
        }
      }
    }
 else {
      msg.append(word);
    }
    msg.append(" ");
  }
  voiceManager.say(msg.toString());
}
