@OnEvent(OnCheckIsSameItemEvent.class) static boolean isSameItem(ComponentContext c,@FromEvent Data previousItem,@FromEvent Data nextItem){
  return previousItem.number == nextItem.number;
}
