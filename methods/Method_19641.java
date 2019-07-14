@OnEvent(ClickEvent.class) static void onClickLayoutProps(ComponentContext c,@Prop ExamplesLithoLabActivity.LabExampleController labExampleController){
  labExampleController.setContentComponent(LearningLayoutPropsComponent.create(c).build());
}
