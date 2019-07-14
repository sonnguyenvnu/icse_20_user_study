public boolean isUndecidedBool(){
  return isBool() && asBool().value == BoolType.Value.Undecided && asBool().s1 != null && asBool().s2 != null;
}
