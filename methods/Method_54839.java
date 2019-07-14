/** 
 * Checks if a command can be send. There can only be 1 outstanding command. 
 */
@NativeType("int") public static boolean b3CanSubmitCommand(@NativeType("b3PhysicsClientHandle") long physClient){
  if (CHECKS) {
    check(physClient);
  }
  return nb3CanSubmitCommand(physClient) != 0;
}
