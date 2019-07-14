@OnEvent(ClickEvent.class) static void onClickState(ComponentContext c,@Prop ExamplesLithoLabActivity.LabExampleController labExampleController){
  labExampleController.setContentComponent(LearningStateComponent.create(c).build());
}
