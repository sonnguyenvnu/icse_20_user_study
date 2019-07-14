/** 
 * Returns the sort of this type reference.
 * @return one of {@link #CLASS_TYPE_PARAMETER},  {@link #METHOD_TYPE_PARAMETER},  {@link #CLASS_EXTENDS},  {@link #CLASS_TYPE_PARAMETER_BOUND},  {@link #METHOD_TYPE_PARAMETER_BOUND}, {@link #FIELD},  {@link #METHOD_RETURN},  {@link #METHOD_RECEIVER},  {@link #METHOD_FORMAL_PARAMETER},  {@link #THROWS},  {@link #LOCAL_VARIABLE},  {@link #RESOURCE_VARIABLE},  {@link #EXCEPTION_PARAMETER},  {@link #INSTANCEOF},  {@link #NEW}, {@link #CONSTRUCTOR_REFERENCE},  {@link #METHOD_REFERENCE},  {@link #CAST},  {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},  {@link #METHOD_INVOCATION_TYPE_ARGUMENT},  {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or  {@link #METHOD_REFERENCE_TYPE_ARGUMENT}.
 */
public int getSort(){
  return targetTypeAndInfo >>> 24;
}
