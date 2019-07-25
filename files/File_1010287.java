/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.lang.smodel;

import gnu.trove.TObjectIntHashMap;
import jetbrains.mps.smodel.adapter.ids.MetaIdFactory;
import jetbrains.mps.smodel.adapter.ids.MetaIdHelper;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import org.jetbrains.mps.openapi.language.SAbstractConcept;

/**
 * Facility to build an effective SConcept->int map for concepts not necessarily from the same language.
 * Not thread-safe, expected usage pattern is {@code new Builder().put().put().seal()} doesn't suggest multi-threading.
 * FIXME move to [smodelRuntime] module
 * @author Artem Tikhomirov
 * @since 3.5
 */
public final class ConceptSwitchIndexBuilder {
  private final TObjectIntHashMap<SConceptId> myConcepts = new TObjectIntHashMap<>();
  private int myNextAvailableIndex = 0;
  // prevent from modification of exposed map instance (don't want to copy map on seal())
  private boolean myIsSealed = false;

  public ConceptSwitchIndexBuilder put(SAbstractConcept c, int i) {
    assert !myIsSealed;
    updateNextAvailableIndex(i);
    myConcepts.put(MetaIdHelper.getConcept(c), i);
    return this;
  }

  public ConceptSwitchIndexBuilder put(SConceptId cid, int i) {
    assert !myIsSealed;
    updateNextAvailableIndex(i);
    myConcepts.put(cid, i);
    return this;
  }

  public ConceptSwitchIndexBuilder put(long uuidHigh, long uuidLow, long concept, int i) {
    assert !myIsSealed;
    updateNextAvailableIndex(i);
    myConcepts.put(MetaIdFactory.conceptId(uuidHigh, uuidLow, concept), i);
    return this;
  }

  public ConceptSwitchIndexBuilder put(SConceptId... cid) {
    assert !myIsSealed;
    if (cid == null) {
      return this;
    }
    int i = myNextAvailableIndex;
    for (SConceptId c : cid) {
      myConcepts.put(c, i++);
    }
    myNextAvailableIndex = i;
    return this;
  }

  /**
   * Use of the builder is not expected once this method has been invoked.
   * XXX perhaps, ConceptIndex return value would be better, jsut need to regenerate a lot.
   */
  public ConceptSwitchIndex seal() {
    assert !myIsSealed;
    myIsSealed = true;
    myConcepts.compact();
    return new ConceptSwitchIndex(myConcepts);
  }

  private void updateNextAvailableIndex(int i) {
    assert i >= myNextAvailableIndex;
    myNextAvailableIndex = i + 1;
  }
}
