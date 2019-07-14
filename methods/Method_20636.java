public void trackSelectedChosenCurrency(final String selectedCurrency){
  this.client.track(KoalaEvent.SELECTED_CHOSEN_CURRENCY,new HashMap<String,Object>(){
{
      put("user_chosen_currency",selectedCurrency);
    }
  }
);
}
