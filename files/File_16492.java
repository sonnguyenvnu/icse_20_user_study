package org.hswebframework.web.organizational.authorization.relation;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 关系信�?�，用于获�?�，判断组织机构中的关系信�?�
 *
 * @author zhouhao
 * @see Relation
 * @since 3.0
 */
public interface Relations extends Serializable {

    /**
     * 判断与目标是�?�存在�?个关系
     * <pre>
     *     //判断是�?�是张三的leader关系
     *     relations.has("leader","person","张三",POSITIVE);
     * </pre>
     * <pre>
     *     //判断张三是�?�是当�?的leader关系
     *     relations.has("leader","person","张三",REVERSE);
     * </pre>
     * <pre>
     *     //判断人员:张三和本人是�?�相互为friend关系
     *     relations.has("friend","person","张三",ALL);
     * </pre>
     *
     * @param relation 关系
     * @param type     关系类型
     * @param to       目标
     * @return 是�?�存在关系
     */
    default boolean has(String relation, String type, String to, Relation.Direction direction) {
        return getAll().stream().anyMatch(rel ->
                rel.getRelation().equals(relation)
                        && rel.getDimension().equals(type)
                        && rel.getTarget().equals(to)
                        && rel.matchDirection(direction));
    }

    default boolean has(String relation, String type, Relation.Direction direction) {
        return getAll().stream().anyMatch(rel ->
                rel.getRelation().equals(relation)
                        && rel.getDimension().equals(type)
                        && rel.matchDirection(direction));
    }


    default boolean has(String relation, Relation.Direction direction) {
        return getAll().stream().anyMatch(rel ->
                rel.getRelation().equals(relation)
                        && rel.matchDirection(direction));
    }

    /**
     * @see this#has(String, String, String, Relation.Direction)
     */
    default boolean has(String relation, String type, String to, String direction) {
        return has(relation, type, to, Relation.Direction.fromString(direction));
    }

    /**
     * use {@link Relation.Direction#POSITIVE}
     *
     * @see this#has(String, String, String, Relation.Direction)
     */
    default boolean has(String relation) {
        return !findAll(relation).isEmpty();
    }

    default boolean hasRev(String relation, String type, String to) {
        return has(relation, type, to, Relation.Direction.REVERSE);
    }

    default boolean hasPos(String relation, String type, String to) {
        return has(relation, type, to, Relation.Direction.POSITIVE);
    }

    default boolean hasRev(String relation, String type) {
        return has(relation, type, Relation.Direction.REVERSE);
    }

    default boolean hasPos(String relation, String type) {
        return has(relation, type, Relation.Direction.POSITIVE);
    }

    default boolean hasPos(String relation) {
        return has(relation, Relation.Direction.POSITIVE);
    }

    default boolean hasRev(String relation) {
        return has(relation, Relation.Direction.REVERSE);
    }

    /**
     * 获�?�指定关系的全部关系信�?�
     *
     * @param relation 关系标识,如: leader
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     * @see this#find(Predicate)
     */
    default List<Relation> findAll(String relation) {
        return find(rel -> rel.getRelation().equals(relation));
    }

    /**
     * 获�?�正�?�关系,如: 我是xxx的relation
     *
     * @param relation 关系标识,如: leader
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     */
    default List<Relation> findPos(String relation) {
        return find(relation, Relation.Direction.POSITIVE);
    }

    /**
     * 获�?��??�?�关系,如: xxx是我的relation
     *
     * @param relation 关系标识,如: leader
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     */
    default List<Relation> findRev(String relation) {
        return find(relation, Relation.Direction.REVERSE);
    }

    /**
     * 获�?�指定关系和方�?�的关系信�?�
     *
     * @param relation  关系标识，例如: leader
     * @param direction 关系方�?�
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     */
    default List<Relation> find(String relation, Relation.Direction direction) {
        return find(rel -> rel.getRelation().equals(relation) && rel.matchDirection(direction));
    }

    /**
     * 获�?�指定关系和维度的全部关系信�?�
     *
     * @param relation 关系标识，例如: leader
     * @param type     关系维度,例如：person
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     * @see this#find(Predicate)
     */
    default List<Relation> findAll(String relation, String type) {
        return find(rel -> rel.getRelation().equals(relation) && rel.getDimension().equals(type));
    }

    /**
     * 获�?�指定关系和类型以�?�方�?�<b>�??�?�关系</b>
     *
     * @param relation 关系标识，例如: leader
     * @param type     关系类型,例如：person
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     * @see this#find(String, String, Relation.Direction)
     */
    default List<Relation> findRev(String relation, String type) {
        return find(relation, type, Relation.Direction.REVERSE);
    }

    /**
     * 获�?�指定关系和类型以�?�方�?�<b>正�?�关系</b>
     *
     * @param relation 关系标识，例如: leader
     * @param type     关系类型,例如：person
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     * @see this#find(String, String, Relation.Direction)
     */
    default List<Relation> findPos(String relation, String type) {
        return find(relation, type, Relation.Direction.POSITIVE);
    }

    /**
     * 获�?�指定关系和类型以�?�方�?�全部关系信�?�
     *
     * @param relation  关系标识，例如: leader
     * @param type      关系类型,例如：person
     * @param direction 关系方�?�
     * @return 关系信�?�集�?�，如果关系�?存在，返回空集�?�
     * @see this#find(Predicate)
     */
    default List<Relation> find(String relation, String type, Relation.Direction direction) {
        return find(rel ->
                rel.getRelation().equals(relation)
                        && rel.getDimension().equals(type)
                        && rel.matchDirection(direction));
    }

    /**
     * @see this#find(String, String, Relation.Direction)
     */
    default List<Relation> find(String relation, String type, String direction) {
        return find(relation, type, Relation.Direction.fromString(direction));
    }

    /**
     * 查找关系
     * <pre>
     *     findAll(rel->rel.getDimension().equals("person"))
     * </pre>
     *
     * @param predicate 查找的判断逻辑
     * @return 满足�?�件的关系信�?�集�?�，如果全部�?满足则返回空集�?�
     */
    default List<Relation> find(Predicate<Relation> predicate) {
        return getAll().stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * @return 全部关系信�?�，如果一个也没有返回空集�?�
     */
    List<Relation> getAll();
}
