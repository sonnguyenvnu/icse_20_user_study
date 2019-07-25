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
import jetbrains.mps.smodel.adapter.ids.MetaIdHelper;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;

/**
 * Effective translation from {@link org.jetbrains.mps.openapi.language.SAbstractConcept SConcept} to integer value, intended for use in
 * concept switch (i.e. runtime component for lang.smodel language construct).
 * Use {@link ConceptSwitchIndexBuilder} to create an index
 *
 * @author Artem Tikhomirov
 * @since 3.5
 */
public final class ConceptSwitchIndex implements ConceptIndex {
  private final TObjectIntHashMap<SConceptId> myConcepts;

  /*package*/ ConceptSwitchIndex(TObjectIntHashMap<SConceptId> map) {
    myConcepts = map;
  }

  @Override
  public int index(@Nullable SAbstractConcept c, int missingValue) {
    if (c == null) {
      return missingValue;
    }
    SConceptId key = MetaIdHelper.getConcept(c);
    // note, contains/get pair is vital, as 0 is legitimate index value, while TObjectIntHashMap.get returns 0 for missing keys
    if (myConcepts.containsKey(key)) {
      return myConcepts.get(key);
    }
    return missingValue;
  }
}
