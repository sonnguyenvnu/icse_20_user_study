/*
 * Copyright 2003-2012 JetBrains s.r.o.
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
package jetbrains.mps.typesystem;

import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.performance.IPerformanceTracer;

import java.util.*;
import java.util.Map.Entry;


public class TypeSystemReporter {
  private static TypeSystemReporter instance = null;
  private boolean isEnabled = false;
  private Map<String, Pair<Long, Long>> myGetTypeOfTime = new HashMap<>();
  private Map<String, Pair<Long, Long>> myIsSubTypeTime = new HashMap<>();
  private Map<String, Pair<Long, Long>> myCoerceTime = new HashMap<>();

  private TypeSystemReporter() {

  }

  public static TypeSystemReporter getInstance() {
    if (instance == null) {
      instance = new TypeSystemReporter();
    }
    return instance;
  }

  public void reset() {
    isEnabled = true;
    myGetTypeOfTime.clear();
    myIsSubTypeTime.clear();
    myCoerceTime.clear();
  }

  public void reportTypeOf(SNode node, long time) {
    if (!isEnabled) return;
    String conceptFqName = node.getConcept().getQualifiedName();
    report(time, conceptFqName, myGetTypeOfTime);
  }

  private void report(long time, String conceptFqName, Map<String, Pair<Long, Long>> map) {
    if (!isEnabled) return;
    Pair<Long, Long> value = map.get(conceptFqName);
    if (value == null) {
      value = new Pair<>(0L, 0L);
      map.put(conceptFqName, value);
    }
    value.o1 += time;
    value.o2++;
  }

  public void reportIsSubType(SNode subType, SNode superType, long time) {
    if (!isEnabled || null == subType || null == superType) return;
    String conceptFqName = subType.getConcept().getQualifiedName() + "   " + superType.getConcept().getQualifiedName();
    report(time, conceptFqName, myIsSubTypeTime);
  }

  public void reportCoerce(SNode subType, SAbstractConcept concept, long time) {
    if (!isEnabled || null == subType) {
      return;
    }
    String conceptFqName = subType.getConcept().getQualifiedName() + "   " + concept.getQualifiedName();
    report(time, conceptFqName, myCoerceTime);
  }

  public void printReport(int numTop, IPerformanceTracer tracer) {
    tracer.addText("Time spent on getTypeOf operation");
    printMapReport(myGetTypeOfTime, numTop, tracer);
    tracer.addText("Time spent on isSubType operation");
    printMapReport(myIsSubTypeTime, numTop, tracer);
    tracer.addText("Time spent on coerce operation");
    printMapReport(myCoerceTime, numTop, tracer);
    isEnabled = false;
  }

  public void printMapReport(Map<String, Pair<Long, Long>> map, int numTop, IPerformanceTracer tracer) {
    if (!isEnabled) return;
    ArrayList<Entry<String, Pair<Long, Long>>> list = new ArrayList<>();
    list.addAll(map.entrySet());

    Collections.sort(list, Comparator.comparing(o -> o.getValue().o1));
    long sum = 0;
    int i = 0;
    for (Entry<String, Pair<Long, Long>> entry : list) {
      if (i++ >= numTop) break;
      sum += entry.getValue().o1;
      StringBuilder sb = new StringBuilder();
      boolean isFirst = true;
      for (String name : entry.getKey().split("   ")) {
        if (!isFirst) {
          sb.append(" :< ");
        }
        isFirst = false;
        int beginIndex = name.lastIndexOf('.')+1;
        sb.append(name.substring(beginIndex));
       
      }
      tracer.addText(String.format(sb + " %.3f s,  %d times", entry.getValue().o1 * 1.0e-9, entry.getValue().o2));
    }
    tracer.addText("Total: " + sum * 1.0e-9);
  }


}
