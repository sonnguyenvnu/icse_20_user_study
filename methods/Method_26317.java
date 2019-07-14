/** 
 * Construct a diagnostic message, e.g.: <ul> <li>This access should be guarded by 'this', which is not currently held <li>This access should be guarded by 'this'; instead found 'mu' <li>This access should be guarded by 'this'; instead found: 'mu1', 'mu2' </ul>
 */
private String buildMessage(GuardedByExpression guard,HeldLockSet locks){
  int heldLocks=locks.allLocks().size();
  StringBuilder message=new StringBuilder();
  Select enclosing=findOuterInstance(guard);
  if (enclosing != null && !enclosingInstance(guard)) {
    if (guard == enclosing) {
      message.append(String.format("Access should be guarded by enclosing instance '%s' of '%s'," + " which is not accessible in this scope",enclosing.sym().owner,enclosing.base()));
    }
 else {
      message.append(String.format("Access should be guarded by '%s' in enclosing instance '%s' of '%s'," + " which is not accessible in this scope",guard.sym(),enclosing.sym().owner,enclosing.base()));
    }
    if (heldLocks > 0) {
      message.append(String.format("; instead found: '%s'",Joiner.on("', '").join(locks.allLocks())));
    }
    return message.toString();
  }
  message.append(String.format("This access should be guarded by '%s'",guard));
  if (guard.kind() == GuardedByExpression.Kind.ERROR) {
    message.append(", which could not be resolved");
    return message.toString();
  }
  if (heldLocks == 0) {
    message.append(", which is not currently held");
  }
 else {
    message.append(String.format("; instead found: '%s'",Joiner.on("', '").join(locks.allLocks())));
  }
  return message.toString();
}
