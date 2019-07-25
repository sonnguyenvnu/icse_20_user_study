public GEConstraint copy(){
  return new OneLabelL2RangeGEConstraints(this.constraints,this.map);
}
