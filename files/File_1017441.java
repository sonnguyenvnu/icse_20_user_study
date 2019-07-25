package com.foxinmy.weixin4j.mp.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.weixin4j.mp.type.ClientPlatformType;
import com.foxinmy.weixin4j.mp.type.Lang;
import com.foxinmy.weixin4j.type.Gender;

/**
 * 个性化�?��?�匹�?规则
 * 
 * @className MenuMatchRule
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年12月17日
 * @since JDK 1.6
 * @see
 */
public class MenuMatchRule implements Serializable {

	private static final long serialVersionUID = 8115117407710728580L;

	private JSONObject matchRule;

	public MenuMatchRule() {
		this.matchRule = new JSONObject();
	}

	/**
	 * 用户标签id，�?�通过用户表情管�?�接�?�获�?�
	 */
	private Integer tagId;

	@JSONField(name = "tag_id")
	public MenuMatchRule group(int tagId) {
		matchRule.put("tag_id", tagId);
		this.tagId = tagId;
		return this;
	}

	/**
	 * 性别
	 */
	private Gender gender;

	@JSONField(name = "sex")
	public void gender0(int sex) {
		this.gender = Gender.values().length >= sex ? Gender.values()[sex - 1]
				: null;
	}

	public MenuMatchRule gender(Gender gender) {
		if (gender != null && gender != Gender.unknown) {
			matchRule.put("sex", gender.ordinal() + 1);
		}
		this.gender = gender;
		return this;
	}

	/**
	 * 客户端版本
	 */
	private ClientPlatformType platformType;

	/**
	 * 请使用 {@link #platform(ClientPlatformType platformType)}}
	 * @param platform
	 */
	@JSONField(name = "client_platform_type")
	public void platform0(int platform) {
		this.platformType = ClientPlatformType.values().length >= platform ? ClientPlatformType
				.values()[platform - 1] : null;
	}

	public MenuMatchRule platform(ClientPlatformType platformType) {
		if (platformType != null) {
			matchRule.put("client_platform_type", platformType.ordinal() + 1);
		}
		this.platformType = platformType;
		return this;
	}

	private String country;

	/**
	 * 国家信�?�，是用户在微信中设置的地区
	 * <p>
	 * country�?province�?city组�?地区信�?�，将按照country�?province�?city的顺�?进行验�?
	 * ，�?符�?�地区信�?�表的内容。地区信�?�从大到�?验�?，�?的�?�以�?填，�?�若填写了�?份信�?�，则国家信�?�也必填并且匹�?，城市信�?��?�以�?填。 例如 “中国
	 * 广东�? 广州市�?�?“中国 广东�?�?都是�?�法的地域信�?�，而“中国 广州市�?则�?�?�法，因为填写了城市信�?�但没有填写�?份信�?�
	 * 
	 * @param country
	 * @return
	 */
	@JSONField(name = "country")
	public MenuMatchRule country(String country) {
		matchRule.put("country", country);
		this.country = country;
		return this;
	}

	private String province;

	/**
	 * �?份信�?�，是用户在微信中设置的地区
	 * <p>
	 * country�?province�?city组�?地区信�?�，将按照country�?province�?city的顺�?进行验�?，�?符�?�地区信�?�表的内容。
	 * 地区信�?�从大到�?验�?，�?的�?�以�?填，�?�若填写了�?份信�?�，则国家信�?�也必填并且匹�?，城市信�?��?�以�?填。 例如 “中国 广东�? 广州市�?�?“中国
	 * 广东�?�?都是�?�法的地域信�?�，而“中国 广州市�?则�?�?�法，因为填写了城市信�?�但没有填写�?份信�?�
	 * 
	 * @param country
	 * @return
	 */
	@JSONField(name = "province")
	public MenuMatchRule province(String province) {
		matchRule.put("province", province);
		this.province = province;
		return this;
	}

	private String city;

	/**
	 * 城市信�?�，是用户在微信中设置的地区
	 * <p>
	 * country�?province�?city组�?地区信�?�，将按照country�?province�?city的顺�?进行验�?，�?符�?�地区信�?�表的内容。
	 * 地区信�?�从大到�?验�?，�?的�?�以�?填，�?�若填写了�?份信�?�，则国家信�?�也必填并且匹�?，城市信�?��?�以�?填。 例如 “中国 广东�? 广州市�?�?“中国
	 * 广东�?�?都是�?�法的地域信�?�，而“中国 广州市�?则�?�?�法，因为填写了城市信�?�但没有填写�?份信�?�
	 * 
	 * @param city
	 * @return
	 */
	@JSONField(name = "city")
	public MenuMatchRule city(String city) {
		matchRule.put("city", city);
		this.city = city;
		return this;
	}

	/**
	 * 语言信�?�，是用户在微信中设置的语言
	 */
	private Lang language;

	/**
	 * 请使用 {@link #language(Lang language)}
	 * @param language
	 */
	@JSONField(name = "language")
	public void language0(int language) {
		this.language = Lang.values().length >= language ? Lang.values()[language - 1]
				: null;
	}

	public MenuMatchRule language(Lang language) {
		if (language != null) {
			matchRule.put("language", language.ordinal() + 1);
		}
		this.language = language;
		return this;
	}

	public ClientPlatformType getPlatformType() {
		return platformType;
	}

	public Integer getTagId() {
		return tagId;
	}

	public Gender getGender() {
		return gender;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public Lang getLanguage() {
		return language;
	}

	public boolean hasRule() {
		return !matchRule.isEmpty();
	}

	public JSONObject getRule() {
		return this.matchRule;
	}

	@Override
	public String toString() {
		return "MenuMatchRule [tagId=" + tagId + ", gender=" + gender
				+ ", platformType=" + platformType + ", country=" + country
				+ ", province=" + province + ", city=" + city + ", language="
				+ language + "]";
	}
}
