static ModelState build(EpoxyModel<?> model,int position,boolean immutableModel){
  ModelState state=new ModelState();
  state.lastMoveOp=0;
  state.pair=null;
  state.id=model.id();
  state.position=position;
  if (immutableModel) {
    state.model=model;
  }
 else {
    state.hashCode=model.hashCode();
  }
  return state;
}
