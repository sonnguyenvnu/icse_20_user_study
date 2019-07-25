/** 
 * Constructs an immutable instance which is the combination of the given instance with the given additional annotation. The latter's type must not already appear in the former.
 * @param annotations {@code non-null;} the instance to augment
 * @param annotation {@code non-null;} the additional annotation
 * @return {@code non-null;} the combination
 * @throws IllegalArgumentException thrown if there is a duplicate type
 */
public static Annotations combine(Annotations annotations,Annotation annotation){
  Annotations result=new Annotations();
  result.addAll(annotations);
  result.add(annotation);
  result.setImmutable();
  return result;
}
