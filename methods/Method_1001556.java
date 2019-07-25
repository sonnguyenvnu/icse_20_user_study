public MinMax update(MinMax minMax){
  for (  Position position : positions.values()) {
    minMax=position.update(minMax);
  }
  return minMax;
}
