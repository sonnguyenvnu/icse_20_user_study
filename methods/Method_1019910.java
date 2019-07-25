/** 
 * Returns true if  {@code a} and {@code b} are equal. 
 */
static boolean equals(@Nullable Type a,@Nullable Type b){
  if (a == b) {
    return true;
  }
 else   if (a instanceof Class) {
    if (b instanceof GenericArrayType) {
      return equals(((Class)a).getComponentType(),((GenericArrayType)b).getGenericComponentType());
    }
    return a.equals(b);
  }
 else   if (a instanceof ParameterizedType) {
    if (!(b instanceof ParameterizedType))     return false;
    ParameterizedType pa=(ParameterizedType)a;
    ParameterizedType pb=(ParameterizedType)b;
    Type[] aTypeArguments=pa instanceof ParameterizedTypeImpl ? ((ParameterizedTypeImpl)pa).typeArguments : pa.getActualTypeArguments();
    Type[] bTypeArguments=pb instanceof ParameterizedTypeImpl ? ((ParameterizedTypeImpl)pb).typeArguments : pb.getActualTypeArguments();
    return equals(pa.getOwnerType(),pb.getOwnerType()) && pa.getRawType().equals(pb.getRawType()) && Arrays.equals(aTypeArguments,bTypeArguments);
  }
 else   if (a instanceof GenericArrayType) {
    if (b instanceof Class) {
      return equals(((Class)b).getComponentType(),((GenericArrayType)a).getGenericComponentType());
    }
    if (!(b instanceof GenericArrayType))     return false;
    GenericArrayType ga=(GenericArrayType)a;
    GenericArrayType gb=(GenericArrayType)b;
    return equals(ga.getGenericComponentType(),gb.getGenericComponentType());
  }
 else   if (a instanceof WildcardType) {
    if (!(b instanceof WildcardType))     return false;
    WildcardType wa=(WildcardType)a;
    WildcardType wb=(WildcardType)b;
    return Arrays.equals(wa.getUpperBounds(),wb.getUpperBounds()) && Arrays.equals(wa.getLowerBounds(),wb.getLowerBounds());
  }
 else   if (a instanceof TypeVariable) {
    if (!(b instanceof TypeVariable))     return false;
    TypeVariable<?> va=(TypeVariable<?>)a;
    TypeVariable<?> vb=(TypeVariable<?>)b;
    return va.getGenericDeclaration() == vb.getGenericDeclaration() && va.getName().equals(vb.getName());
  }
 else {
    return false;
  }
}
