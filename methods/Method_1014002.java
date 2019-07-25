/** 
 * Maps item DTO into item object.
 * @param itemDTO the DTO
 * @param itemBuilderFactory the item registry
 * @return the item object
 */
public static @Nullable Item map(ItemDTO itemDTO,ItemBuilderFactory itemBuilderFactory){
  if (itemDTO == null) {
    throw new IllegalArgumentException("The argument 'itemDTO' must no be null.");
  }
  if (itemBuilderFactory == null) {
    throw new IllegalArgumentException("The argument 'itemBuilderFactory' must no be null.");
  }
  if (itemDTO.type != null) {
    ItemBuilder builder=itemBuilderFactory.newItemBuilder(itemDTO.type,itemDTO.name);
    if (itemDTO instanceof GroupItemDTO && GroupItem.TYPE.equals(itemDTO.type)) {
      GroupItemDTO groupItemDTO=(GroupItemDTO)itemDTO;
      Item baseItem=null;
      if (!StringUtils.isEmpty(groupItemDTO.groupType)) {
        baseItem=itemBuilderFactory.newItemBuilder(groupItemDTO.groupType,itemDTO.name).build();
        builder.withBaseItem(baseItem);
      }
      GroupFunction function=new GroupFunction.Equality();
      if (groupItemDTO.function != null) {
        function=mapFunction(baseItem,groupItemDTO.function);
      }
      builder.withGroupFunction(function);
    }
    builder.withLabel(itemDTO.label);
    builder.withCategory(itemDTO.category);
    builder.withGroups(itemDTO.groupNames);
    builder.withTags(itemDTO.tags);
    try {
      return builder.build();
    }
 catch (    IllegalStateException e) {
      return null;
    }
  }
  return null;
}
