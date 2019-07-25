@Override public void execute(String[] args,Console console){
  if (args.length > 0) {
    String itemName=args[0];
    try {
      Item item=this.itemRegistry.getItemByPattern(itemName);
      console.println(item.getState().toString());
    }
 catch (    ItemNotFoundException e) {
      console.println("Error: Item '" + itemName + "' does not exist.");
    }
catch (    ItemNotUniqueException e) {
      console.print("Error: Multiple items match this pattern: ");
      for (      Item item : e.getMatchingItems()) {
        console.print(item.getName() + " ");
      }
    }
  }
 else {
    printUsage(console);
  }
}
