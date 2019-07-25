public double update(double newInput){
  double newOutput=a1 * newInput + a2 * this.inputHistory[0] + a3 * this.inputHistory[1] - b1 * this.outputHistory[0] - b2 * this.outputHistory[1];
  this.inputHistory[1]=this.inputHistory[0];
  this.inputHistory[0]=newInput;
  this.outputHistory[2]=this.outputHistory[1];
  this.outputHistory[1]=this.outputHistory[0];
  this.outputHistory[0]=newOutput;
  return this.outputHistory[0];
}
