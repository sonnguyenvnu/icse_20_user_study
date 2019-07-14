@Override protected boolean validateMisfireInstruction(int misfireInstruction){
  if (misfireInstruction < MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY) {
    return false;
  }
  return misfireInstruction <= MISFIRE_INSTRUCTION_DO_NOTHING;
}
