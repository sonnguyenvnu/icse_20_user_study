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
package jetbrains.mps.generator.impl.plan;

import jetbrains.mps.generator.impl.MapCfgGroups;
import jetbrains.mps.generator.impl.MapCfgGroups.ByModule;
import jetbrains.mps.generator.runtime.TemplateMappingConfiguration;
import jetbrains.mps.util.CollectionUtil;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collection of TemplateMappingConfigurations as a unit of generation plan.
* @author Artem Tikhomirov
*/
@Immutable
final class Group {
  private final Set<TemplateMappingConfiguration> myMappings;
  private final boolean myIsTopPriority;
  private final int myHash;

  private Group(Set<TemplateMappingConfiguration> mappings, boolean topPri) {
    myMappings = mappings;
    myIsTopPriority = topPri;
    myHash = myMappings.hashCode();
  }

  public Group() {
    this(Collections.emptySet(), false);
  }

  public Group(@NotNull TemplateMappingConfiguration cfg) {
    this(Collections.singleton(cfg), cfg.isTopPriority());
  }

  public Group(Iterable<Group> other) {
    HashSet<TemplateMappingConfiguration> mappings = new HashSet<>();
    HashMap<Boolean, Group> topPri = new HashMap<>();
    for (Group g : other) {
      mappings.addAll(g.myMappings);
      topPri.put(g.isTopPriority(), g);
    }
    if (topPri.size() != 1) {
      throw new IllegalArgumentException(String.format("Can't create a group from a set of groups with different 'top priority' setting: %s", other));
    }
    myMappings = mappings;
    myIsTopPriority = topPri.keySet().iterator().next();
    myHash = myMappings.hashCode();
  }

  public Group subtract(Group other) {
    final HashSet<TemplateMappingConfiguration> mc = new HashSet<>(myMappings);
    mc.removeAll(other.myMappings);
    return new Group(mc, myIsTopPriority);
  }

  public Group union(Group other) {
    return new Group(Arrays.asList(this, other));
  }

  public Collection<TemplateMappingConfiguration> getElements() {
    return myMappings;
  }

  public boolean isEmpty() {
    return myMappings.isEmpty();
  }

  public boolean isTopPriority() {
    return myIsTopPriority;
  }

  @Override
  public int hashCode() {
    return myHash;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (false == other instanceof Group) {
      return false;
    }
    return myMappings.equals(((Group) other).myMappings);
  }

  public boolean hasCommonMappings(Group other) {
    return CollectionUtil.intersects(myMappings, other.myMappings);
  }

  public boolean includes(Group other) {
    if (other.myMappings.size() > myMappings.size()) {
      return false;
    }
    if (other.isEmpty()) {
      return isEmpty();
    }
    return myMappings.containsAll(other.myMappings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isTopPriority()) {
      sb.append("Top");
    }
    if (isEmpty()) {
      sb.append("Empty");
    }
    sb.append("Group[");
    for (ByModule chunk : new MapCfgGroups(myMappings).groupByModule()) {
      sb.append(NameUtil.compactNamespace(chunk.getKey().getAlias()));
      chunk.getElements().map(TemplateMappingConfiguration::getName).collect(Collectors.joining(",", ":{", "}; "));
    }
    sb.append(']');
    return sb.toString();
  }
}
