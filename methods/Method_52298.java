public boolean checksNothing(){
  return getProperty(CHECK_BREAK_LOOP_TYPES).isEmpty() && getProperty(CHECK_CONTINUE_LOOP_TYPES).isEmpty() && getProperty(CHECK_RETURN_LOOP_TYPES).isEmpty();
}
