String dump(){
  flags|=FLAG_ESCAPE;
  return super.dump() + " " + CustomLogicModel.escape(text);
}
