/** 
 * Label appended to all public exception strings, used to help find which RV in an app is hitting an exception.
 */
String exceptionLabel(){
  return " " + super.toString() + ", adapter:" + mAdapter + ", layout:" + mLayout + ", context:" + getContext();
}
