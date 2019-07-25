/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.persistence.registry;

import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* @author Artem Tikhomirov
*/
public final class LangInfo extends BaseInfo implements Comparable<LangInfo> {
  private final SLanguageId myLanguageId;
  private final String myName;
  private final List<ConceptInfo> myConcepts = new ArrayList<>();

  /*package*/LangInfo(@NotNull SLanguageId languageId, @NotNull String langaugeName) {
    myLanguageId = languageId;
    myName = langaugeName;
  }
  public SLanguageId getLanguageId() {
    return myLanguageId;
  }
  public String getName() {
    return myName;
  }

  /*package*/ void register(@NotNull ConceptInfo ci) {
    // the reason we pass ConceptInfo here and do not check for duplicates is the fact we ensure single
    // concept occurrence with IdInfoCollector.myRegistry. LangInfo merely serves as a view for myRegistry of concepts
    myConcepts.add(ci);
  }

  public List<ConceptInfo> getConceptsInUse() {
    ArrayList<ConceptInfo> rv = new ArrayList<>(myConcepts);
    Collections.sort(rv);
    return rv;
  }

  @Override
  /*package*/ int internalKey() {
    return (myLanguageId.hashCode() & 0x7fffffff);
  }

  @Override
  public int compareTo(@NotNull LangInfo o) {
    return  internalKey() - o.internalKey();
  }
}
