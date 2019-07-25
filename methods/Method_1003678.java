/** 
 * Delegates to  {@link #impose(Impositions,Factory)}, with  {@code this} as the impositions.
 * @param during the function to execute while the impositions are imposed
 * @param < T > the type of result of the given function
 * @return the result of the given function
 * @throws Exception any thrown by {@code during}
 */
public <T>T impose(Factory<? extends T> during) throws Exception {
  return impose(this,during);
}
