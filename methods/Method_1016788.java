public PRConstraint copy(){
  return new OneLabelL2IndPRConstraints(this.constraints,this.map,this.normalized);
}
