private void list(Console console,Collection<ItemChannelLink> itemChannelLinks){
  for (  ItemChannelLink itemChannelLink : itemChannelLinks) {
    console.println(itemChannelLink.toString());
  }
}
