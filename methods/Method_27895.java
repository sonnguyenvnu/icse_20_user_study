private void loadContributions(ArrayList<ContributionsDay> contributions,GitHubContributionsView gitHubContributionsView){
  List<ContributionsDay> filter=gitHubContributionsView.getLastContributions(contributions);
  if (filter != null && contributions != null) {
    Observable<Bitmap> bitmapObservable=Observable.just(gitHubContributionsView.drawOnCanvas(filter,contributions));
    manageObservable(bitmapObservable.doOnNext(bitmap -> sendToView(view -> view.onInitContributions(bitmap != null))));
  }
}
