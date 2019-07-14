private void iniUi(){
  setSupportActionBar(toolbar);
  toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
  toolbar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
  findViewById(R.id.donate_googleplay_card).setVisibility(View.GONE);
  btnDonatePP.setText(getString(R.string.donate).toUpperCase());
  btnDonatePP.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      cts.launchUrl("https://www.paypal.me/HoraApps");
    }
  }
);
  findViewById(R.id.donate_bitcoin_item).setOnLongClickListener(new View.OnLongClickListener(){
    @Override public boolean onLongClick(    View v){
      ClipboardManager clipboard=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
      ClipData clip=ClipData.newPlainText("HoraApps BTC",((TextView)v).getText());
      clipboard.setPrimaryClip(clip);
      StringUtils.showToast(getApplicationContext(),getString(R.string.address_copied));
      return true;
    }
  }
);
}
