private static Modifier psiModifierToModifier(PsiElement psiModifier){
switch (psiModifier.getText()) {
case PsiModifier.ABSTRACT:
    return Modifier.ABSTRACT;
case PsiModifier.FINAL:
  return Modifier.FINAL;
case PsiModifier.NATIVE:
return Modifier.NATIVE;
case PsiModifier.PRIVATE:
return Modifier.PRIVATE;
case PsiModifier.PROTECTED:
return Modifier.PROTECTED;
case PsiModifier.PUBLIC:
return Modifier.PUBLIC;
case PsiModifier.STATIC:
return Modifier.STATIC;
case PsiModifier.STRICTFP:
return Modifier.STRICTFP;
case PsiModifier.SYNCHRONIZED:
return Modifier.SYNCHRONIZED;
case PsiModifier.TRANSIENT:
return Modifier.TRANSIENT;
case PsiModifier.VOLATILE:
return Modifier.VOLATILE;
default :
throw new ComponentsProcessingException("Unexpected Modifier, modifier is: " + psiModifier.getText());
}
}
