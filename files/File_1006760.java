package io.xream.x7.demo.bean;

import org.apache.commons.collections.MapUtils;
import x7.core.repository.X;
import x7.core.web.IdView;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Cat implements Serializable, IdView {

	private static final long serialVersionUID = 5708147778966785698L;

	@X.Key
	private long id;
	@X.Mapping("cat_type")
	private String type;
	private String taxType;
	private long dogId;
	private long test;
	private List<Long> list;

	private transient Map<Object,Object> viewMap;
	@Override
	public void transform(Map<Object,Object> viewMap) {
		this.viewMap = viewMap;
	}

	public String getName(){
		return MapUtils.getString(viewMap,""+id);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public long getDogId() {
		return dogId;
	}

	public void setDogId(long dogId) {
		this.dogId = dogId;
	}

	public long getTest() {
		return test;
	}

	public void setTest(long test) {
		this.test = test;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {

		this.list = list;
	}

	@Override
	public String toString() {
		return "zxt.oop.xxx.Cat{" +
				"id=" + id +
				", type='" + type + '\'' +
				", taxType='" + taxType + '\'' +
				'}';
	}
}
