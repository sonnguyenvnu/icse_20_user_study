@OnEvent(ClickEvent.class) static void onClickLayoutSpecs(ComponentContext c,@Prop ExamplesLithoLabActivity.LabExampleController labExampleController){
  labExampleController.setContentComponent(LearningLayoutSpecsComponent.create(c).build());
}
