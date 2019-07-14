/** 
 * Puts the given target_type and target_info JVMS structures into the given ByteVector.
 * @param targetTypeAndInfo a target_type and a target_info structures encoded as in {@link #targetTypeAndInfo}. LOCAL_VARIABLE and RESOURCE_VARIABLE target types are not supported.
 * @param output where the type reference must be put.
 */
static void putTarget(final int targetTypeAndInfo,final ByteVector output){
switch (targetTypeAndInfo >>> 24) {
case CLASS_TYPE_PARAMETER:
case METHOD_TYPE_PARAMETER:
case METHOD_FORMAL_PARAMETER:
    output.putShort(targetTypeAndInfo >>> 16);
  break;
case FIELD:
case METHOD_RETURN:
case METHOD_RECEIVER:
output.putByte(targetTypeAndInfo >>> 24);
break;
case CAST:
case CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT:
case METHOD_INVOCATION_TYPE_ARGUMENT:
case CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT:
case METHOD_REFERENCE_TYPE_ARGUMENT:
output.putInt(targetTypeAndInfo);
break;
case CLASS_EXTENDS:
case CLASS_TYPE_PARAMETER_BOUND:
case METHOD_TYPE_PARAMETER_BOUND:
case THROWS:
case EXCEPTION_PARAMETER:
case INSTANCEOF:
case NEW:
case CONSTRUCTOR_REFERENCE:
case METHOD_REFERENCE:
output.put12(targetTypeAndInfo >>> 24,(targetTypeAndInfo & 0xFFFF00) >> 8);
break;
default :
throw new IllegalArgumentException();
}
}
