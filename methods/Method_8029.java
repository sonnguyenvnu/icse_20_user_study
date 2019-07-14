public void setOnArrowClickListener(final OnClickListener onClickListener){
  arrowView.setOnClickListener(v -> {
    accountsShowed=!accountsShowed;
    arrowView.setImageResource(accountsShowed ? R.drawable.collapse_up : R.drawable.collapse_down);
    onClickListener.onClick(DrawerProfileCell.this);
    arrowView.setContentDescription(accountsShowed ? LocaleController.getString("AccDescrHideAccounts",R.string.AccDescrHideAccounts) : LocaleController.getString("AccDescrShowAccounts",R.string.AccDescrShowAccounts));
  }
);
}
