/** 
 * Parse a modifier token into a  {@link Modifier}. 
 */
@Nullable private static Modifier getTokModifierKind(ErrorProneToken tok){
switch (tok.kind()) {
case PUBLIC:
    return Modifier.PUBLIC;
case PROTECTED:
  return Modifier.PROTECTED;
case PRIVATE:
return Modifier.PRIVATE;
case ABSTRACT:
return Modifier.ABSTRACT;
case STATIC:
return Modifier.STATIC;
case FINAL:
return Modifier.FINAL;
case TRANSIENT:
return Modifier.TRANSIENT;
case VOLATILE:
return Modifier.VOLATILE;
case SYNCHRONIZED:
return Modifier.SYNCHRONIZED;
case NATIVE:
return Modifier.NATIVE;
case STRICTFP:
return Modifier.STRICTFP;
case DEFAULT:
return Modifier.DEFAULT;
default :
return null;
}
}
