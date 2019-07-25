void execute(){
  if (pins[0].value && prevInputValue != pins[0].value && (retriggerable || !triggered)) {
    lastRisingEdge=sim.t;
    pins[1].value=true;
    pins[2].value=false;
    triggered=true;
  }
  if (triggered && sim.t > lastRisingEdge + delay) {
    pins[1].value=false;
    pins[2].value=true;
    triggered=false;
  }
  prevInputValue=pins[0].value;
}
