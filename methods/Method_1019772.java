public void update(CTP paragraph){
  if (changeSection) {
    changeSection=false;
    if (!isEmpty()) {
      currentSectPr=super.poll();
      fireSectionChanged(currentSectPr);
    }
 else {
      currentSectPr=bodySectPr;
      fireSectionChanged(currentSectPr);
    }
  }
 else {
    CTSectPr sectPr=getSectPr(paragraph);
    if (sectPr != null) {
      currentSectPr=sectPr;
      changeSection=true;
    }
  }
}
