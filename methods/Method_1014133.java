private static EnrichedItemDTO map(Item item,ItemDTO itemDTO,URI uri,boolean drillDown,Predicate<Item> itemFilter,Locale locale){
  String state=item.getState().toFullString();
  String transformedState=considerTransformation(state,item,locale);
  if (transformedState != null && transformedState.equals(state)) {
    transformedState=null;
  }
  StateDescription stateDescription=considerTransformation(item.getStateDescription(locale));
  String link=null != uri ? uri.toASCIIString() + ItemResource.PATH_ITEMS + "/" + itemDTO.name : null;
  EnrichedItemDTO enrichedItemDTO=null;
  if (item instanceof GroupItem) {
    GroupItem groupItem=(GroupItem)item;
    EnrichedItemDTO[] memberDTOs;
    if (drillDown) {
      Collection<EnrichedItemDTO> members=new LinkedHashSet<>();
      for (      Item member : groupItem.getMembers()) {
        if (itemFilter == null || itemFilter.test(member)) {
          members.add(map(member,drillDown,itemFilter,uri,locale));
        }
      }
      memberDTOs=members.toArray(new EnrichedItemDTO[members.size()]);
    }
 else {
      memberDTOs=new EnrichedItemDTO[0];
    }
    enrichedItemDTO=new EnrichedGroupItemDTO(itemDTO,memberDTOs,link,state,transformedState,stateDescription);
  }
 else {
    enrichedItemDTO=new EnrichedItemDTO(itemDTO,link,state,transformedState,stateDescription,item.getCommandDescription(locale));
  }
  return enrichedItemDTO;
}
