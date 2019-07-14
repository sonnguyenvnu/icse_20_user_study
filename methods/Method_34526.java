private static Result equals(Type commandType,Type fallbackType){
  if (isParametrizedType(commandType) && isParametrizedType(fallbackType)) {
    final ParameterizedType pt1=(ParameterizedType)commandType;
    final ParameterizedType pt2=(ParameterizedType)fallbackType;
    Result result=regularEquals(pt1.getRawType(),pt2.getRawType());
    return result.andThen(new Supplier<Result>(){
      @Override public Result get(){
        return FallbackMethod.equals(pt1.getActualTypeArguments(),pt2.getActualTypeArguments());
      }
    }
);
  }
 else   if (isTypeVariable(commandType) && isTypeVariable(fallbackType)) {
    final TypeVariable tv1=(TypeVariable)commandType;
    final TypeVariable tv2=(TypeVariable)fallbackType;
    if (tv1.getGenericDeclaration() instanceof Method && tv2.getGenericDeclaration() instanceof Method) {
      Result result=equals(tv1.getBounds(),tv2.getBounds());
      return result.append(new Supplier<List<Error>>(){
        @Override public List<Error> get(){
          return Collections.singletonList(boundsError(tv1,tv1.getBounds(),"",tv2,tv2.getBounds()));
        }
      }
);
    }
    return regularEquals(tv1,tv2);
  }
 else   if (isWildcardType(commandType) && isWildcardType(fallbackType)) {
    final WildcardType wt1=(WildcardType)commandType;
    final WildcardType wt2=(WildcardType)fallbackType;
    Result result=equals(wt1.getLowerBounds(),wt2.getLowerBounds());
    result=result.append(new Supplier<List<Error>>(){
      @Override public List<Error> get(){
        return Collections.singletonList(boundsError(wt1,wt1.getLowerBounds(),"lower",wt2,wt2.getLowerBounds()));
      }
    }
);
    if (result.isFailure())     return result;
    result=equals(wt1.getUpperBounds(),wt2.getUpperBounds());
    return result.append(new Supplier<List<Error>>(){
      @Override public List<Error> get(){
        return Collections.singletonList(boundsError(wt1,wt1.getUpperBounds(),"upper",wt2,wt2.getUpperBounds()));
      }
    }
);
  }
 else {
    return regularEquals(commandType,fallbackType);
  }
}
