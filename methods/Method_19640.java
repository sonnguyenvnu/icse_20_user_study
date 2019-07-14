@OnEvent(ClickEvent.class) static void onClickTextWidget(ComponentContext c,@Prop ExamplesLithoLabActivity.LabExampleController labExampleController){
  labExampleController.setContentComponent(LearningTextWidgetComponent.create(c).build());
}
