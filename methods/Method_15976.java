private <T>void validate(Supplier<Set<ConstraintViolation<T>>> validatorSetFunction){
  if (validator == null) {
    logger.warn("validator is null!");
    return;
  }
  SimpleValidateResults results=new SimpleValidateResults();
  validatorSetFunction.get().forEach(violation -> results.addResult(violation.getPropertyPath().toString(),violation.getMessage()));
  if (!results.isSuccess()) {
    throw new ValidationException(results);
  }
}
