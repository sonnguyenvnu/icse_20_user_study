/** 
 * Returns the execution controller bound to the current thread, if this is a Ratpack managed compute thread. <p> If called on a non Ratpack compute thread, the returned optional will be empty.
 * @return the execution controller for the current thread
 */
static Optional<ExecController> current(){
  return ExecThreadBinding.maybeGet().map(ExecThreadBinding::getExecController);
}
