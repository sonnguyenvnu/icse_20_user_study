String dump(){
  ScopePlot vPlot=plots.get(0);
  CircuitElm elm=vPlot.elm;
  if (elm == null)   return null;
  int flags=getFlags();
  int eno=sim.locateElm(elm);
  if (eno < 0)   return null;
  String x="o " + eno + " " + vPlot.speed + " " + vPlot.value + " " + flags + " " + scale[UNITS_V] + " " + scale[UNITS_A] + " " + position + " " + plots.size();
  int i;
  for (i=0; i < plots.size(); i++) {
    ScopePlot p=plots.get(i);
    if (i > 0)     x+=" " + sim.locateElm(p.elm) + " " + p.value;
    if (p.units > UNITS_A)     x+=" " + scale[p.units];
  }
  if (text != null)   x+=" " + text;
  return x;
}
