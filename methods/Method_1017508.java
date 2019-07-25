private void init(){
  setOrdering(ORDERING_TOGETHER);
  addTransition(new ChangeBounds()).addTransition(new ChangeTransform()).addTransition(new ChangeImageTransform());
}
