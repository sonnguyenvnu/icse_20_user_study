String dump(){
  flags|=FLAG_ESCAPE;
  return super.dump() + " " + size + " " + CustomLogicModel.escape(text);
}
