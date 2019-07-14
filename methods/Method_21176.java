private Observable<String> errorMessages(){
  return this.viewModel.outputs.tfaCodeMismatchError().map(__ -> this.codeMismatchString).mergeWith(this.viewModel.outputs.genericTfaError().map(__ -> this.unableToLoginString));
}
