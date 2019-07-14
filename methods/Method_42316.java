private void initUi(){
  setSupportActionBar(toolbar);
  toolbar.setNavigationIcon(new IconicsDrawable(this).icon(CommunityMaterial.Icon.cmd_arrow_left).color(Color.WHITE).sizeDp(19));
  toolbar.setNavigationOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      onBackPressed();
    }
  }
);
  toolbar.setTitle(getString(org.horaapps.leafpic.R.string.donate));
  btnDonateIap=(Button)findViewById(R.id.button_donate_play_store);
  btnDonateIap.setText(String.format("%s %d€",getString(R.string.donate).toUpperCase(),progress));
  bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
    @Override public void onProgressChanged(    SeekBar seekBar,    int i,    boolean b){
      if (i == 0)       progress=2;
 else       progress=(i + 1) * 2;
      btnDonateIap.setText(String.format("%s %d€",getString(R.string.donate).toUpperCase(),progress));
    }
    @Override public void onStartTrackingTouch(    SeekBar seekBar){
    }
    @Override public void onStopTrackingTouch(    SeekBar seekBar){
    }
  }
);
  btnDonatePP=(Button)findViewById(R.id.button_donate_paypal);
  btnDonatePP.setText(getString(R.string.donate).toUpperCase());
  findViewById(org.horaapps.leafpic.R.id.button_donate_paypal).setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      cts.launchUrl("https://www.paypal.me/HoraApps");
    }
  }
);
  findViewById(org.horaapps.leafpic.R.id.donate_bitcoin_item).setOnLongClickListener(new View.OnLongClickListener(){
    @Override public boolean onLongClick(    View v){
      ClipboardManager clipboard=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
      ClipData clip=ClipData.newPlainText("HoraApps BTC",((TextView)v).getText());
      clipboard.setPrimaryClip(clip);
      StringUtils.showToast(getApplicationContext(),getString(org.horaapps.leafpic.R.string.address_copied));
      return true;
    }
  }
);
  findViewById(R.id.button_donate_play_store).setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View view){
      if (mHelper != null)       mHelper.flagEndAsync();
      mHelper.launchPurchaseFlow(DonateActivity.this,"donation_" + progress,123,new IabHelper.OnIabPurchaseFinishedListener(){
        @Override public void onIabPurchaseFinished(        IabResult result,        Purchase info){
          mHelper.consumeAsync(info,mPurchaseFinishedListener);
        }
      }
);
    }
  }
);
}
