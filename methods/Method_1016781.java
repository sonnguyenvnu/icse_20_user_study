public GEConstraint copy(){
  return new TwoLabelL2GEConstraints(this.constraintsList,this.constraintsMap,this.map);
}
