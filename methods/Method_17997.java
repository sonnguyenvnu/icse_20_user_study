/** 
 * Given two object instances of the same type, this method accesses all their internal fields, including the fields of StateContainer if the class type is Component, to check if they are equivalent. There's special equality code to handle special class types e.g. Components, EventHandlers, etc.
 * @param obj1
 * @param obj2
 * @return true if the two instances are equivalent. False otherwise.
 */
public static boolean hasEquivalentFields(Object obj1,Object obj2){
  if (obj1 == null || obj2 == null || obj1.getClass() != obj2.getClass()) {
    throw new IllegalArgumentException("The input is invalid.");
  }
  for (  Field field : obj1.getClass().getDeclaredFields()) {
    if (!field.isAnnotationPresent(Comparable.class)) {
      continue;
    }
    final Class<?> classType=field.getType();
    final Object val1;
    final Object val2;
    try {
      field.setAccessible(true);
      val1=field.get(obj1);
      val2=field.get(obj2);
      field.setAccessible(false);
    }
 catch (    IllegalAccessException e) {
      throw new IllegalStateException("Unable to get fields by reflection.",e);
    }
    @Comparable.Type int comparableType=field.getAnnotation(Comparable.class).type();
switch (comparableType) {
case Comparable.FLOAT:
      if (Float.compare((Float)val1,(Float)val2) != 0) {
        return false;
      }
    break;
case Comparable.DOUBLE:
  if (Double.compare((Double)val1,(Double)val2) != 0) {
    return false;
  }
break;
case Comparable.ARRAY:
if (!areArraysEquals(classType,val1,val2)) {
return false;
}
break;
case Comparable.PRIMITIVE:
if (!val1.equals(val2)) {
return false;
}
break;
case Comparable.COMPARABLE_DRAWABLE:
if (!((ComparableDrawable)val1).isEquivalentTo((ComparableDrawable)val2)) {
return false;
}
break;
case Comparable.COLLECTION_COMPLEVEL_0:
final Collection c1=(Collection)val1;
final Collection c2=(Collection)val2;
if (c1 != null ? !c1.equals(c2) : c2 != null) {
return false;
}
break;
case Comparable.COLLECTION_COMPLEVEL_1:
case Comparable.COLLECTION_COMPLEVEL_2:
case Comparable.COLLECTION_COMPLEVEL_3:
case Comparable.COLLECTION_COMPLEVEL_4:
int level=comparableType - Comparable.COLLECTION_COMPLEVEL_0;
if (!areComponentCollectionsEquals(level,(Collection)val1,(Collection)val2)) {
return false;
}
break;
case Comparable.COMPONENT:
case Comparable.SECTION:
if (val1 != null ? !((Equivalence)val1).isEquivalentTo(val2) : val2 != null) {
return false;
}
break;
case Comparable.EVENT_HANDLER:
case Comparable.EVENT_HANDLER_IN_PARAMETERIZED_TYPE:
if (val1 != null ? !((EventHandler)val1).isEquivalentTo((EventHandler)val2) : val2 != null) {
return false;
}
break;
case Comparable.OTHER:
if (val1 != null ? !val1.equals(val2) : val2 != null) {
return false;
}
break;
case Comparable.STATE_CONTAINER:
if (!hasEquivalentFields(val1,val2)) {
return false;
}
break;
}
}
return true;
}
