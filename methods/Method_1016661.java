private SelectSubQueryImpl<T> from(final FromClause fromClause){
  selectionCore.clearSelection();
  SelectSubQueryImpl<T> subSelect=new SelectSubQueryImpl<>(fromClause,selectionCore.columns,selectionCore.isDistinct);
  subSelect.setParent(getParent());
  return subSelect;
}
