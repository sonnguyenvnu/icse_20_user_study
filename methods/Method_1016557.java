/** 
 * Returns the "canonical" form for this particular result (only possible if isResolvable). 
 */
public String canonical(){
switch (type) {
case CONVERGE:
    return steps.get(steps.size() - 1);
case CYCLE:
  return Collections.min(steps,Comparator.comparing(String::length).thenComparing(Function.identity()));
case DIVERGE:
throw new IllegalArgumentException("No canonical form for a diverging result");
default :
throw new IllegalArgumentException("Unknown type: " + type);
}
}
