private void guardAgainstDuplicateSubmission(){
  if (executed)   throw Exceptions.computerHasAlreadyBeenSubmittedAVertexProgram();
 else   executed=true;
}
