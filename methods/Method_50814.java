public void setMaxRuleViolations(int max){
  if (max >= 0) {
    this.maxRuleViolations=max;
    this.failOnRuleViolation=true;
  }
}
