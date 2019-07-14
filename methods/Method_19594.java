@OnCreateInitialState static void onCreateInitialState(ComponentContext c,StateValue<RecyclerCollectionEventsController> recyclerEventsController,StateValue<DynamicValue<Float>> handleTranslation,StateValue<ScrollController> scrollController){
  final RecyclerCollectionEventsController recyclerEventsControllerValue=new RecyclerCollectionEventsController();
  final DynamicValue<Float> handleTranslationValue=new DynamicValue<>(0f);
  final ScrollController scrollControllerValue=new ScrollController(recyclerEventsControllerValue,handleTranslationValue);
  recyclerEventsController.set(recyclerEventsControllerValue);
  handleTranslation.set(handleTranslationValue);
  scrollController.set(scrollControllerValue);
}
