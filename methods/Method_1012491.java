String dump(){
  dumped=true;
  return "34 " + CustomLogicModel.escape(name) + " " + flags + " " + saturationCurrent + " " + seriesResistance + " " + emissionCoefficient + " " + breakdownVoltage;
}
