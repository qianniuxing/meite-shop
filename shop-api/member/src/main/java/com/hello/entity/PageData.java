package com.hello.entity;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

public class PageData extends HashMap<Object, Object> implements Map<Object, Object>, Serializable {

	private static final long serialVersionUID = 1L;

	Map<Object, Object> map = null;
	HttpServletRequest request;

	public PageData(HttpServletRequest request) {
		this.request = request;
		Map<String, String[]> properties = request.getParameterMap();
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();

			if (null == valueObj) {
				returnMap.put(name, "");
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				if (values.length == 1) {
					returnMap.put(name, cleanXSS(values[0]));
				} else {
					String[] strAppay = new String[values.length];
					for (int i = 0; i < values.length; i++) {
						strAppay[i] = cleanXSS(values[i]);
					}
					returnMap.put(name, strAppay);
				}
			} else {
				returnMap.put(name, cleanXSS(valueObj.toString()));
			}

		}
		map = returnMap;
	}


	private String cleanXSS(String value) {
		// value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		// value = value.replaceAll("%3c", "& lt;").replaceAll("%3e", "& gt;");
		// value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "&
		// #41;");
		// value = value.replaceAll("%28", "& #40;").replaceAll("%29", "&
		// #41;");
		// value = value.replaceAll("'", "& #39;");
		// value = value.replaceAll("%27", "& #39;");
		// value = value.replaceAll("eval\\((.*)\\)", "");
		// value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
		// "\"\"");
		// value = value.replaceAll("script", "");
		return value;
	}

	public PageData() {
		map = new HashMap<Object, Object>();
	}

	@Override
	public Object get(Object key) {
		if (map.containsKey(key))
			return map.get(key);
		else
			return null;
	}

	// 获取页条数
	public int getLimit() {
		if (map.containsKey("limit")) {
			return Integer.parseInt(map.get("limit") + "");
		} else {
			return 10;
		}
	}

	// 获取页码
	public int getPage() {
		if (map.containsKey("page")) {
			return Integer.parseInt(map.get("page") + "");
		} else {
			return 1;
		}
	}

	// 返回string类型
	public String getString(Object key) {
		if (null == get(key)) return "";
		else return (String) (get(key) + "");
	}

	// 返回Integer类型
	public Integer getInteger(Object key) {
		if (null == get(key)) return 0;
		else return (Integer) get(key);
	}

	//返回字符串转Integer
	public Integer getParseInt(Object key) {
		if (null == get(key) && "".equals(getString(key))) return null;
		else return Integer.parseInt(getString(key));
	}

	// 返回Double类型
	public Double getDouble(Object key) {
		if (null == get(key)) return 0.0;
		else return (Double) get(key);
	}

	// 返回Float类型
	public Float getFloat(Object key) {
		if (null == get(key)) return 0.0f;
		else return (Float) get(key);
	}

	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set<Entry<Object, Object>> entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<Object> keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		if (isEmpty()) {
			str.append(" map is empty.");
		} else {
			Iterator it = keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				str.append(key).append(":").append(map.get(key)).append(" ");
			}
		}
		return str.toString();
	}
}
