private void loadComment(int page,long commentId,String login,String repoId,List<TimelineModel> timeline){
  if (page == 1 && commentId > 0) {
    Observable<TimelineModel> observable=Observable.create(source -> {
      int index=-1;
      if (timeline != null) {
        for (int i=0; i < timeline.size(); i++) {
          TimelineModel timelineModel=timeline.get(i);
          if (timelineModel.getComment() != null) {
            if (timelineModel.getComment().getId() == commentId) {
              index=i;
              break;
            }
          }
        }
      }
      TimelineModel timelineModel=new TimelineModel();
      timelineModel.setPosition(index);
      source.onNext(timelineModel);
      source.onComplete();
    }
);
    manageObservable(observable.doOnNext(timelineModel -> sendToView(view -> {
      if (timelineModel.getComment() != null) {
        view.addComment(timelineModel,-1);
      }
 else {
        view.addComment(null,timelineModel.getPosition());
      }
    }
)));
  }
}
