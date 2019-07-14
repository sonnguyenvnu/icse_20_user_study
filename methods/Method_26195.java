private boolean isConfusing(Kind thisKind,Kind parentKind){
  if (CONDITIONAL.contains(thisKind) && CONDITIONAL.contains(parentKind)) {
    return true;
  }
  return (SHIFT.contains(thisKind) && ARITHMETIC.contains(parentKind)) || (SHIFT.contains(parentKind) && ARITHMETIC.contains(thisKind));
}
