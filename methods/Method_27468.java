@Override public void bind(@NonNull CommitFileChanges commitFileChanges){
  CommitFileModel commit=commitFileChanges.getCommitFileModel();
  toggle.setVisibility(commit.getPatch() == null ? View.GONE : View.VISIBLE);
  name.setText(commit.getFilename());
  changes.setText(SpannableBuilder.builder().append(changesText).append("\n").bold(String.valueOf(commit.getChanges())));
  addition.setText(SpannableBuilder.builder().append(additionText).append("\n").bold(String.valueOf(commit.getAdditions())));
  deletion.setText(SpannableBuilder.builder().append(deletionText).append("\n").bold(String.valueOf(commit.getDeletions())));
  status.setText(SpannableBuilder.builder().append(statusText).append("\n").bold(String.valueOf(commit.getStatus())));
  int position=getAdapterPosition();
  onToggle(onToggleView.isCollapsed(position),false,position);
}
