/** 
 * Builds a new validator from a predicate, and description.
 * @param pred                  The predicate. If it returnsfalse on a value, then the value is deemed to have a problem
 * @param constraintDescription Description of the constraint,see  {@link PropertyConstraint#getConstraintDescription()}.
 * @param < U >                   Type of value to validate
 * @return A new validator
 */
@Experimental public static <U>PropertyConstraint<U> fromPredicate(final Predicate<? super U> pred,final String constraintDescription){
  return new PropertyConstraint<U>(){
    @Override public boolean test(    U value){
      return pred.test(value);
    }
    @Override public String validate(    U value){
      return pred.test(value) ? null : "Constraint violated on property value '" + value + "' (" + constraintDescription + ")";
    }
    @Override public String getConstraintDescription(){
      return StringUtils.capitalize(constraintDescription);
    }
    @Override public PropertyConstraint<Iterable<? extends U>> toCollectionConstraint(){
      final PropertyConstraint<U> thisValidator=this;
      return ConstraintFactory.<Iterable<? extends U>>fromPredicate(new Predicate<Iterable<? extends U>>(){
        @Override public boolean test(        Iterable<? extends U> us){
          for (          U u : us) {
            if (!thisValidator.test(u)) {
              return false;
            }
          }
          return true;
        }
      }
,"Components " + StringUtils.uncapitalize(thisValidator.getConstraintDescription()));
    }
  }
;
}
