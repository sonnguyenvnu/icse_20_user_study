private void clear(Console console){
  Collection<ItemChannelLink> itemChannelLinks=itemChannelLinkRegistry.getAll();
  for (  ItemChannelLink itemChannelLink : itemChannelLinks) {
    itemChannelLinkRegistry.remove(itemChannelLink.getUID());
  }
  console.println(itemChannelLinks.size() + " links successfully removed.");
}
