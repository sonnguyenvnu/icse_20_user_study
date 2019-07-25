@Override public void _commit(boolean finished) throws CommitStepException {
  super._commit(finished);
  myGenerator.setDependencyKind(DependencyStep.DependencyKind.values()[mySelectedIndex]);
}
