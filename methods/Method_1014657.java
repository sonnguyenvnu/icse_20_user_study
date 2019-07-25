private void calculate(Map<Double,Object> labelOverrideMap){
  if (minValue == maxValue) {
    String label=labelOverrideMap.isEmpty() ? " " : labelOverrideMap.values().iterator().next().toString();
    tickLabels.add(label);
    tickLocations.add(workingSpace / 2.0);
    return;
  }
  double tickSpace=styler.getPlotContentSize() * workingSpace;
  if (tickSpace < styler.getXAxisTickMarkSpacingHint()) {
    return;
  }
  double margin=Utils.getTickStartOffset(workingSpace,tickSpace);
  for (  Entry<Double,Object> entry : labelOverrideMap.entrySet()) {
    Object value=entry.getValue();
    String tickLabel=value == null ? " " : value.toString();
    tickLabels.add(tickLabel);
    double tickLabelPosition=margin + ((entry.getKey().doubleValue() - minValue) / (maxValue - minValue) * tickSpace);
    tickLocations.add(tickLabelPosition);
  }
}
