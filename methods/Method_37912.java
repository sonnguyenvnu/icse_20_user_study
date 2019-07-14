/** 
 * When set to  {@code true}, environment will not be copied from the parent process and will be completly empty.
 */
public CommandLine newEnv(final boolean clean){
  cleanEnvironment=clean;
  return this;
}
