public void overwrite(@NotNull State s){
  this.table=s.table;
  this.parent=s.parent;
  this.stateType=s.stateType;
  this.forwarding=s.forwarding;
  this.supers=s.supers;
  this.globalNames=s.globalNames;
  this.type=s.type;
  this.path=s.path;
}
