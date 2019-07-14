/** 
 * @see PropertySource#dysfunctionReason()
 */
@Override public String dysfunctionReason(){
  return allowsAllAssignments() ? "All assignment types allowed, no checks performed" : null;
}
