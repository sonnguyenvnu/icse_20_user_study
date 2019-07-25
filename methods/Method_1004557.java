@Override public void selected(SimpleSymbol def){
  if (def == null) {
    enablePanning();
  }
  if (def.canDraw()) {
    ModifierPicker picker=new ModifierPicker();
    picker.show(getActivity(),def);
    if (def.getMaxPoints() == 1) {
      enablePanning();
      plotter.setSymbol(def);
      Toast.makeText(getActivity(),"Long press to plot!",Toast.LENGTH_SHORT).show();
    }
    if (def.getMinPoints() > 1) {
      enablePainting();
      paint.setSymbol(def);
      Toast.makeText(getActivity(),"Draw on the screen!",Toast.LENGTH_SHORT).show();
    }
  }
 else {
    enablePanning();
    Toast.makeText(getActivity(),"Symbol cannot be plotted, try another!",Toast.LENGTH_SHORT).show();
  }
}
