/*
 * Copyright 2003-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.smodel.references;

import jetbrains.mps.smodel.StaticReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.event.SNodeAddEvent;
import org.jetbrains.mps.openapi.event.SNodeRemoveEvent;
import org.jetbrains.mps.openapi.event.SReferenceChangeEvent;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelListenerBase;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeChangeListenerAdapter;
import org.jetbrains.mps.openapi.model.SNodeUtil;

import java.util.HashSet;
import java.util.Set;

public class ImmatureReferencesTracker {
  private SModel myModel = null;
  private SNodeChangeListenerAdapter myNodeListener = new MySNodeChangeListenerAdapter();
  private Set<StaticReference> myImmatureRefs = new HashSet<>();
  private SModelListenerBase myModelListener = new MySModelListenerBase();

  public void attach(SModel model, boolean doCheckImmediately) {
    assert myModel == null;
    myModel = model;
    myModel.addChangeListener(myNodeListener);
    myModel.addModelListener(myModelListener);

    if (doCheckImmediately){
      addRefsFromModel(model);
    }
  }

  public void detach() {
    myModel.removeModelListener(myModelListener);
    myModel.removeChangeListener(myNodeListener);
    myModel = null;
  }

  public void makeMature() {
    for (StaticReference r : myImmatureRefs) {
      if (!r.isDirect()) {
        continue;
      }
      r.makeIndirect();
    }
    myImmatureRefs.clear();
  }

  private void checkAndAddNewRef(org.jetbrains.mps.openapi.model.SReference newRef) {
    if (!(newRef instanceof StaticReference)) {
      return;
    }
    StaticReference newRefBase = (StaticReference) newRef;
    if (!newRefBase.isDirect()) {
      return;
    }

    myImmatureRefs.add(newRefBase);
  }

  private void addRefsFromModel(SModel model) {
    for (SNode n: SNodeUtil.getDescendants(model)){
      for (org.jetbrains.mps.openapi.model.SReference r : n.getReferences()) {
        checkAndAddNewRef(r);
      }
    }
  }

  private class MySModelListenerBase extends SModelListenerBase {
    @Override
    public void modelReplaced(SModel model) {
      addRefsFromModel(model);
    }
  }

  private class MySNodeChangeListenerAdapter extends SNodeChangeListenerAdapter {
    @Override
    public void referenceChanged(@NotNull SReferenceChangeEvent event) {
      org.jetbrains.mps.openapi.model.SReference newRef = event.getNewValue();
      checkAndAddNewRef(newRef);
    }

    @Override
    public void nodeAdded(@NotNull SNodeAddEvent event) {
      for (SNode n: SNodeUtil.getDescendants(event.getChild())){
        for (org.jetbrains.mps.openapi.model.SReference r : n.getReferences()) {
          checkAndAddNewRef(r);
        }
      }
    }

    @Override
    public void nodeRemoved(@NotNull SNodeRemoveEvent event) {
      for (SNode n: SNodeUtil.getDescendants(event.getChild())){
        for (org.jetbrains.mps.openapi.model.SReference r : n.getReferences()) {
          if (!(r instanceof StaticReference)) {
            continue;
          }

          myImmatureRefs.remove(r);
        }
      }
    }
  }
}
