/** 
 * Sets regulation effective.
 * @param regulationEffective the regulation effective
 */
public void setRegulationEffective(boolean regulationEffective){
  this.regulationEffective=regulationEffective;
  FaultToleranceConfigManager.calcEnable();
}
