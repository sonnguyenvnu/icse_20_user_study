void draw(Graphics g){
  if (plots.size() == 0)   return;
  if (scopeTimeStep != sim.timeStep) {
    scopeTimeStep=sim.timeStep;
    resetGraph();
  }
  if (plot2d) {
    draw2d(g);
    return;
  }
  drawSettingsWheel(g);
  g.context.save();
  g.setColor(Color.red);
  g.context.translate(rect.x,rect.y);
  if (showFFT) {
    drawFFTVerticalGridLines(g);
    drawFFT(g);
  }
  int i;
  for (i=0; i != UNITS_COUNT; i++) {
    expandRange[i]=false;
    if (maxScale)     scale[i]=1e-4;
  }
  int si;
  somethingSelected=false;
  for (si=0; si != visiblePlots.size(); si++) {
    ScopePlot plot=visiblePlots.get(si);
    calcPlotScale(plot);
    if (sim.scopeSelected == -1 && plot.elm != null && plot.elm.isMouseElm())     somethingSelected=true;
    expandRange[plot.units]=true;
  }
  checkForSelection();
  if (selectedPlot >= 0)   somethingSelected=true;
  drawGridLines=true;
  boolean hGridLines=true;
  for (i=1; i < visiblePlots.size(); i++) {
    if (visiblePlots.get(i).units != visiblePlots.get(0).units)     hGridLines=false;
  }
  if ((hGridLines || showMax || showMin) && visiblePlots.size() > 0)   calcMaxAndMin(visiblePlots.firstElement().units);
  for (i=0; i != visiblePlots.size(); i++) {
    if (visiblePlots.get(i).units > UNITS_A && i != selectedPlot)     drawPlot(g,visiblePlots.get(i),hGridLines,false);
  }
  for (i=0; i != visiblePlots.size(); i++) {
    if (visiblePlots.get(i).units == UNITS_A && i != selectedPlot)     drawPlot(g,visiblePlots.get(i),hGridLines,false);
  }
  for (i=0; i != visiblePlots.size(); i++) {
    if (visiblePlots.get(i).units == UNITS_V && i != selectedPlot)     drawPlot(g,visiblePlots.get(i),hGridLines,false);
  }
  if (selectedPlot >= 0)   drawPlot(g,visiblePlots.get(selectedPlot),hGridLines,true);
  if (visiblePlots.size() > 0)   drawInfoTexts(g);
  g.context.restore();
  drawCrosshairs(g);
  if (plots.get(0).ptr > 5 && !lockScale) {
    for (i=0; i != UNITS_COUNT; i++)     if (scale[i] > 1e-4 && expandRange[i])     scale[i]/=2;
  }
}
