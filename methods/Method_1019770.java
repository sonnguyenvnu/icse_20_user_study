public void initialize() throws Exception {
  this.initialized=true;
  compute(document);
  if (isEmpty()) {
    currentSectPr=bodySectPr;
    addSection(currentSectPr,false);
    fireSectionChanged(currentSectPr);
  }
 else {
    currentSectPr=super.poll();
    fireSectionChanged(currentSectPr);
  }
}
