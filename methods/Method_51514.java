/** 
 * Merges the given report into this report. This might be necessary, if a summary over all violations is needed as PMD creates one report per file by default.
 * @param r the report to be merged into this.
 * @see AbstractAccumulatingRenderer
 */
public void merge(Report r){
  Iterator<ProcessingError> i=r.errors();
  while (i.hasNext()) {
    addError(i.next());
  }
  Iterator<ConfigurationError> ce=r.configErrors();
  while (ce.hasNext()) {
    addConfigError(ce.next());
  }
  Iterator<Metric> m=r.metrics();
  while (m.hasNext()) {
    addMetric(m.next());
  }
  Iterator<RuleViolation> v=r.iterator();
  while (v.hasNext()) {
    RuleViolation violation=v.next();
    int index=Collections.binarySearch(violations,violation,RuleViolationComparator.INSTANCE);
    violations.add(index < 0 ? -index - 1 : index,violation);
    violationTree.addRuleViolation(violation);
  }
  Iterator<SuppressedViolation> s=r.getSuppressedRuleViolations().iterator();
  while (s.hasNext()) {
    suppressedRuleViolations.add(s.next());
  }
}
