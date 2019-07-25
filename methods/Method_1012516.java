void combine(Scope s){
  plots=visiblePlots;
  plots.addAll(s.visiblePlots);
  s.plots.removeAllElements();
}
