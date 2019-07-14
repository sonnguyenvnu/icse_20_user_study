@OnUpdateState static void updateSelectedPageIndex(StateValue<Integer> selectedPage,StateValue<Integer> firstVisibleIndex,StateValue<Integer> movingDirection,@Param boolean next){
  final int prevPageIndex=selectedPage.get();
  final int newPageIndex=next ? Math.min(prevPageIndex + 1,PAGES_COUNT - 1) : Math.max(prevPageIndex - 1,0);
  if (newPageIndex == prevPageIndex) {
    movingDirection.set(DIRECTION_NONE);
    return;
  }
  movingDirection.set(next ? DIRECTION_RIGHT : DIRECTION_LEFT);
  selectedPage.set(newPageIndex);
  final int firstVisible=firstVisibleIndex.get();
  if (next) {
    final int lastVisible=firstVisible + WINDOW_SIZE - 1;
    if (lastVisible == PAGES_COUNT - 1) {
      return;
    }
    if (newPageIndex < lastVisible) {
      return;
    }
    firstVisibleIndex.set(firstVisible + 1);
  }
 else {
    if (firstVisible == 0) {
      return;
    }
    if (newPageIndex > firstVisible) {
      return;
    }
    firstVisibleIndex.set(firstVisible - 1);
  }
}
