private static ItemChannelLinkDTO map(ItemChannelLink itemChannelLink){
  return new ItemChannelLinkDTO(itemChannelLink.getItemName(),itemChannelLink.getLinkedUID().toString(),itemChannelLink.getConfiguration().getProperties());
}
