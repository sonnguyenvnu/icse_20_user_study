/** 
 * Returns a  {@link Description} of the error based on the rejected JUnit4 construct in the JUnit3class.
 */
private Description makeDescription(String rejectedJUnit4,Tree tree){
  Description.Builder builder=buildDescription(tree).setMessage(String.format("%s cannot be used inside a JUnit3 class. Convert your class to JUnit4 style.",rejectedJUnit4));
  return builder.build();
}
